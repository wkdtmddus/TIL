package com.ssafy.ios.lineup.backend.application.service.chat;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMember;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.chat.ChatMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.ChatErrorCode.NO_CHAT_ROOM;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMemberServiceImpl implements ChatMemberService {

    final private ChatMemberRepository chatMemberRepository;

    @Override
    public ChatMember selectChatMemberByChatRoomAndUser(ChatRoom chatRoom, User user) {
        return chatMemberRepository.findByChatRoomAndUser(chatRoom, user)
                .orElseThrow(() -> new CustomException(NO_CHAT_ROOM));
    }

    @Override
    public void exitChatRoom(ChatMember chatMember) {
        chatMember.updateEnterAtToNull();
        chatMemberRepository.save(chatMember);
    }

    @Override
    public void createChatMember(ChatRoom chatRoom, Application application) {
        ChatMember applicant = ChatMember.builder()
                .chatRoom(chatRoom)
                .user(application.getApplicant())
                .build();
        chatMemberRepository.save(applicant);

        ChatMember writer = ChatMember.builder()
                .chatRoom(chatRoom)
                .user(application.getRecruit().getWriter())
                .build();
        chatMemberRepository.save(writer);
    }
}
