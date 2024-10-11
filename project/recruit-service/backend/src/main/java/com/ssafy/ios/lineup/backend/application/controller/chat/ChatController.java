package com.ssafy.ios.lineup.backend.application.controller.chat;

import com.ssafy.ios.lineup.backend.application.facade.chat.ChatFacade;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageReadRequest;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageRequest;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.dto.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-rooms")
public class ChatController {

    private final ChatFacade chatFacade;

    /* 채팅방 모아보기 */
    @GetMapping
    @Transactional
    public ResponseEntity<?> getChatRooms() {
        List<ChatRoomResponse> chatRooms = chatFacade.getChatRooms();

        return new ResponseEntity<>(chatRooms, HttpStatus.OK);
    }

    /* 웹소켓 : 채팅 전송 */
    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessageRequest chatMessageRequest) {
        chatFacade.sendChatMessage(chatMessageRequest);
    }

    /* 웹소켓 : 메세지 읽음 처리 */
    @MessageMapping("/chat/message/read")
    public void readMessage(ChatMessageReadRequest chatMessageReadRequest) {
        chatFacade.markMessageRead(chatMessageReadRequest.getChatMessageId(), chatMessageReadRequest.getReaderId());
    }

    /* 채팅방 나가기 */
    @DeleteMapping(path = "/{chat-room-id}")
    public ResponseEntity<?> exitChatRoom(
            @PathVariable("chat-room-id") Long chatRoomId) {

        chatFacade.exitChatRoom(chatRoomId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* 채팅방 입장 : 예전 메세지 불러오기 */
    @GetMapping(path = "/{chat-room-id}")
    public ResponseEntity<?> loadChatRoomMessages(
            @PathVariable("chat-room-id") Long chatRoomId) {
        log.info("입장 시 스레드: {}", Thread.currentThread().getName());
        List<ChatMessageResponse> chatMessages = chatFacade.getChatMessages(chatRoomId);

        return new ResponseEntity<>(chatMessages, HttpStatus.OK);

        /* 입장할 때 자신이 공고자인지 지원자인지 값까지 같이 반환하는 버전 */
//        Boolean isApplicant = chatFacade.isApplicant(chatRoomId);
//
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("chatMessages", chatMessages);
//        responseBody.put("isApplicant", isApplicant.toString());
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /* 채팅 전송 */
//    @PostMapping(path = "/{chat-room-id}",
//            consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
//    public ResponseEntity<?> sendMessage(
//            @PathVariable("chat-room-id") Long chatRoomId,
//            @RequestPart(value = "text", required = false) String text,
//            @RequestPart(value = "file", required = false) MultipartFile file) {
//
//        if (text == null) {
//            chatFacade.sendFileMessage(chatRoomId, file);
//        } else if (file == null) {
//            chatFacade.sendTextMessage(chatRoomId, text);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
