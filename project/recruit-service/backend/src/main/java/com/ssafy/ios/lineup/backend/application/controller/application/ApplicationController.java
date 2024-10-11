package com.ssafy.ios.lineup.backend.application.controller.application;

import com.ssafy.ios.lineup.backend.application.facade.application.ApplicationFacade;
import com.ssafy.ios.lineup.backend.application.facade.chat.ChatFacade;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomEnteringResponse;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/* 공고 지원 처리하는 컨트롤러 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationFacade applicationFacade;
    private final ChatFacade chatFacade;

    /* 을이 공고에 지원하기 */
    /* 지원한 사람이 요청하면 그냥 바로 채팅방 id 보내기 */
    @PostMapping(path = "/recruits/{recruit-id}/applications")
    public ResponseEntity<?> createApplication(
            @PathVariable("recruit-id") Long recruitId) {
        // application check 먼저 해서 분기점
        Application searchedApplication = applicationFacade.searchApplication(recruitId);
        if (searchedApplication != null) {
            Long chatRoomId = chatFacade.getChatRoomId(searchedApplication);
            ChatRoomEnteringResponse chatRoomEnteringResponse = new ChatRoomEnteringResponse().builder().
                    chatRoomId(chatRoomId).
                    recruitId(recruitId).build();
            return new ResponseEntity<>(chatRoomEnteringResponse, HttpStatus.OK);
        } else {
            Application application = applicationFacade.registerApplication(recruitId);
            ChatRoom chatRoom = chatFacade.createChat(application);
            Long chatRoomId = applicationFacade.setChatRoom(application, chatRoom);
            ChatRoomEnteringResponse chatRoomEnteringResponse = new ChatRoomEnteringResponse().builder().
                    chatRoomId(chatRoomId).
                    recruitId(recruitId).build();
            return new ResponseEntity<>(chatRoomEnteringResponse, HttpStatus.CREATED);
        }
    }

    /* 갑을 공동 공고지원 취소하기 */
    @DeleteMapping("/recruit-applications/{application-id}/contract-request")
    public ResponseEntity<?> cancelApplication(
            @PathVariable("application-id") Long applicationId) {
        Application application = applicationFacade.cancelApplication(applicationId);
        chatFacade.exitChatRoomByApplication(application);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
