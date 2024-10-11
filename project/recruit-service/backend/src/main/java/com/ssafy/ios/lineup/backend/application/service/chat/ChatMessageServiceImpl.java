package com.ssafy.ios.lineup.backend.application.service.chat;

import com.ssafy.ios.lineup.backend.common.constant.ContentType;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMember;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMessage;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.ChatErrorCode.NO_CHAT_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    final private ChatMessageRepository chatMessageRepository;
    final private FileUtil fileUtil;

    @Override
    public ChatMessageResponse createTextMessage(ChatMember chatMember, String text) throws CustomException {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatMember(chatMember)
                .contentType(ContentType.TEXT)
                .content(text)
                .build();

        ChatMessage savedChatMessage = chatMessageRepository.save(chatMessage);

        return ChatMessageResponse.builder()
                .chatMessageId(savedChatMessage.getId())
                .senderId(savedChatMessage.getSenderId())
                .chatRoomId(savedChatMessage.getChatRoomId())
                .contentType(savedChatMessage.getContentType())
                .content(savedChatMessage.getContent())
                .createdAt(savedChatMessage.getCreatedAt())
                .isRead(savedChatMessage.getIsRead())
                .build();
    }

    @Override
    public void createFileMessage(ChatMember chatMember, MultipartFile file)
            throws CustomException {
        String uuid = UUID.randomUUID().toString();
        fileUtil.saveMessageImg(file, uuid);
        ChatMessage chatMessage = ChatMessage.builder()
                .chatMember(chatMember)
                .contentType(ContentType.IMAGE)
                .content(uuid + "." + fileUtil.getExtension(file))
                .build();
        chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatRoomResponse> putRecentMessage(List<ChatRoomResponse> chatRooms)
            throws CustomException {
        for (ChatRoomResponse chatRoom : chatRooms) {
            ChatMessage recentMessage = getRecentMessageByChatRoomId(chatRoom.getChatRoomId());
            if (recentMessage != null) {
                switch (recentMessage.getContentType()) {
                    case IMAGE -> chatRoom.setRecentMessage("이미지");
                    case TEXT -> chatRoom.setRecentMessage(recentMessage.getContent());
                    case SYSTEM -> chatRoom.setRecentMessage(recentMessage.getContent());
                    default -> {
                        chatRoom.setIsRead(recentMessage.getIsRead());
                        chatRoom.setCreatedAt(recentMessage.getCreatedAt());
                    }
                }
            }
        }
        return chatRooms;
    }

    @Override
    public ChatMessage getRecentMessageByChatRoomId(Long chatRoomId) throws CustomException {
        return chatMessageRepository.findTopByChatRoomIdOrderByCreatedAtDesc(chatRoomId);
    }

    @Override
    public List<ChatMessageResponse> getAllMessages(Long chatRoomId, User user)
            throws CustomException {

        return chatMessageRepository.findAllByChatRoomIdOrderByCreatedAtDesc(chatRoomId).stream()
                .map(chatMessage -> {
                    if (chatMessage.getSenderId().equals(user.getId())) {
                        chatMessage.getIsRead();
                    } else if (!chatMessage.getIsRead()) {
                        chatMessage.readMessage();
                        chatMessageRepository.save(chatMessage);
                    }

                    return ChatMessageResponse.builder()
                            .chatMessageId(chatMessage.getId())
                            .senderId(chatMessage.getSenderId())
                            .contentType(chatMessage.getContentType())
                            .content(chatMessage.getContent())
                            .createdAt(chatMessage.getCreatedAt())
                            .isRead(chatMessage.getIsRead())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public void markMessageRead(Long messageId, Long readerId) throws CustomException {
        ChatMessage chatMessage = chatMessageRepository.findById(messageId).orElseThrow(() -> new CustomException(NO_CHAT_MESSAGE));
        log.info("reader: {}", readerId);
        log.info("sender: {}", chatMessage.getSenderId());
        if (!chatMessage.getSenderId().equals(readerId)) {
            chatMessage.readMessage();
            chatMessageRepository.save(chatMessage);
        }
    }
}
