"use client";

import React, { useEffect, useRef, useState } from "react";
import styles from "./ChatMessages.module.css";
import { Client, StompHeaders } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import api from "../../../pages/api/api";

interface ChatMessage {
  chatMessageId: number;
  chatRoomId: number;
  senderId: number;
  content: string;
  createdAt: string;
  isRead: string;
}

interface ChatMessagesProps {
  chatRoomId: number;
  userId: number | null;
}

const baseURL = process.env.NEXT_PUBLIC_BACK_PORT;

const ChatMessages: React.FC<ChatMessagesProps> = ({ chatRoomId, userId }) => {
  const [chatDetails, setChatDetails] = React.useState<ChatMessage[]>([]);
  const [inputValue, setInputValue] = React.useState("");
  const bottomRef = useRef<HTMLDivElement>(null);
  const stompClientRef = useRef<Client | null>(null);

  const token = localStorage.getItem("Authorization");
  const accessToken = useRef<String | null>(null);
  const getUserId = localStorage.getItem('userId')

  useEffect(() => {
    if (typeof window !== "undefined") {
      accessToken.current = token;
    }
    if (!userId) {
      userId = Number(getUserId);
    }
  }, [token, getUserId]);

  useEffect(() => {
    scrollToBottom();
  }, [chatDetails]);

  const scrollToBottom = () => {
    bottomRef.current?.scrollIntoView();
  };

  const formatTime = (createdAt: string | Date | null) => {
    if (createdAt) {
      const date = new Date(createdAt);
      const localTime = new Date(date.getTime() - date.getTimezoneOffset() * 60000);
      let hours = localTime.getHours();
      const minutes = localTime.getMinutes().toString().padStart(2, "0");
      const ampm = hours >= 12 ? "오후" : "오전";
      hours = hours % 12 || 12;
      return `${ampm} ${hours}:${minutes}`;
    } else {
      const now = new Date();
      let hours = now.getHours();
      const minutes = now.getMinutes().toString().padStart(2, "0");
      const ampm = hours >= 12 ? "오후" : "오전";
      hours = hours % 12 || 12;
      return `${ampm} ${hours}:${minutes}`;
    }
  };


  useEffect(() => {
    const fetchMessages = async () => {
      try {
        const response = await api.get(`/chat-rooms/${chatRoomId}`);
        setChatDetails(response.data);
      } catch (error) {
        console.error("Failed to load chat messages", error);
      }
    };

    fetchMessages();
  }, [chatRoomId]);

  const connectWebSocket = async () => {
    const accessToken = localStorage.getItem("Authorization");
    const socket = new SockJS(`${baseURL}/ws`);
    const client = new Client({
      webSocketFactory: () => socket,
      debug: (str) => console.log(str),
      connectHeaders: {
        Authorization: `Bearer ${accessToken}`,
      } as StompHeaders,
      onConnect: () => {
        stompClientRef.current = client;
        client.subscribe(`/sub/chat/room/${chatRoomId}`, (message) => {
          const newMessage = JSON.parse(message.body);
          if (!newMessage.isRead && newMessage.senderId !== userId) {
            markAsRead(newMessage.chatMessageId);
          }
          setChatDetails((prevMessages) => {
            const updatedMessages = prevMessages.map((msg) =>
              msg.isRead ? msg : { ...msg, isRead: true }
            );
            return [...updatedMessages, newMessage];
          });
        });
      },
      onStompError: async (frame) => {
        console.error("Broker reported error: " + frame.headers["message"]);
        console.error("Additional details: " + frame.body);

        if (frame.headers["message"] === "401 Unauthorized") {
          try {
            await refreshTokenAndReconnect();
          } catch (error) {
            console.error("토큰 갱신 실패:", error);
            alert("로그인을 다시 해주세요.");
            window.location.href = "/login";
          }
        }
      },
    });

    client.activate();

    return client;
  };

  const markAsRead = (chatMessageId: number) => {
    if (stompClientRef.current) {
      const messagePayload = {
        'readerId': userId,
        'chatMessageId': chatMessageId,
      };
      console.log(userId)
      stompClientRef.current.publish({
        destination: "/pub/chat/message/read",
        body: JSON.stringify(messagePayload),
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
    }
  };

  const refreshTokenAndReconnect = async () => {
    try {
      const refreshToken = localStorage.getItem("refreshToken");
      const response = await api.get(`/auth/refresh`, {
        headers: {
          refreshToken: `Bearer ${refreshToken}`,
          "Content-Type": "application/json",
        },
      });

      const newAccessToken = response.data.Authorization;
      const newRefreshToken = response.data.refreshToken;

      localStorage.setItem("Authorization", newAccessToken);
      localStorage.setItem("refreshToken", newRefreshToken);

      await connectWebSocket();
    } catch (error) {
      console.error("토큰 갱신 실패:", error);
      throw error;
    }
  };

  useEffect(() => {
    const fetchMessages = async () => {
      try {
        const response = await api.get(`/chat-rooms/${chatRoomId}`);
        setChatDetails(response.data);
      } catch (error) {
        console.error("Failed to load chat messages", error);
      }
    };

    fetchMessages();
  }, [chatRoomId]);

  useEffect(() => {
    if (chatRoomId) {
      connectWebSocket();
    }
    return () => {
      if (stompClientRef.current) {
        stompClientRef.current.deactivate();
      }
    };
  }, [chatRoomId]);

  const handleSend = () => {
    if (inputValue.trim() !== "" && stompClientRef.current) {
      const newMessage = {
        chatRoomId: chatRoomId,
        senderId: userId,
        content: inputValue,
      };
      stompClientRef.current.publish({
        destination: "/pub/chat/message",
        body: JSON.stringify(newMessage),
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      console.log(newMessage);
      scrollToBottom();
      setInputValue("");
    } else {
      console.log(
        "Cannot send message: stompClientRef.current is null or message is empty"
      );
    }
  };

  return (
    <div className={styles.chatContent}>
      <div className={styles.chatBox}>
        <div className={styles.chatMessages}>
          {chatDetails.map((chat) => (
            <div
              key={chat.chatMessageId}
              className={`${styles.messageBubble} ${chat.senderId === userId
                ? styles.myMessage
                : styles.otherMessage
                }`}
            >
              <p
                className={`${styles.chatMsg} ${chat.senderId === userId
                  ? styles.myChatMsg
                  : styles.otherChatMsg
                  }`}
              >
                {chat.content}
              </p>
              <div
                className={`${styles.msgInfo} ${chat.senderId === userId
                  ? styles.myMsgInfo
                  : styles.otherMsgInfo
                  }`}
              >
                {!chat.isRead && <span className={styles.chatCheck}>1</span>}
                <span className={styles.chatTime}>
                  {formatTime(chat.createdAt)}
                </span>
              </div>
            </div>
          ))}
          <div ref={bottomRef} />
        </div>
      </div>

      <div className={styles.msgInputContainer}>
        <div className={styles.inputPlus}>
          <img src="/image/inputPlus.png" alt="inputPlus" />
        </div>
        <div className={styles.inputBox}>
          <textarea
            className={styles.msgInput}
            placeholder="메시지 보내기"
            value={inputValue}
            onChange={(e) => setInputValue(e.target.value)}
          />
        </div>
        <div className={styles.inputSend} onClick={handleSend}>
          <img src="/image/paperplane.png" alt="send" />
        </div>
      </div>
    </div>
  );
};

export default ChatMessages;
