package com.ssafy.ios.lineup.backend.application.service.chat;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMember;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.ChatErrorCode.NO_CHAT_ROOM;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final FileUtil fileUtilS3;

    @Override
    @Transactional
    public List<ChatRoomResponse> getChatRoomsByUser(User user) throws CustomException {
        List<ChatRoom> chatRooms = chatRoomRepository.findByUser(user); // loginUser
//        for (ChatRoom chatRoom : chatRooms) {
//            System.out.println(chatRoom.getApplication().getApplicant().getId());
//            System.out.println(chatRoom.getApplication().getApplicant().getId().equals(user.getId()) ? chatRoom.getApplication().getRecruit().getWriter().getNickname() : user.getNickname());
//        }

        return chatRooms.stream()
                .map(chatRoom -> ChatRoomResponse.builder()
                        .chatRoomId(chatRoom.getChatRoomId())
                        .opponent(chatRoom.getApplication().getApplicant().getId().equals(user.getId()) ? chatRoom.getApplication().getRecruit().getWriter() : chatRoom.getApplication().getApplicant())
                        .recruitId(chatRoom.getApplication().getRecruit().getId())
                        .fileUtil(fileUtilS3)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatRoomResponse> getChatRoomsByRecruitId(Long recruitId) throws CustomException {
        List<ChatRoom> chatRooms = chatRoomRepository.findByRecruitId(recruitId);
        // todo : ChatRoomResponse의 recentMessage 내용을 채우려면 ChatRoomService에서 ChatMessage를 참조?
        return chatRooms.stream()
                .map(chatRoom -> ChatRoomResponse.builder()
                        .chatRoomId(chatRoom.getChatRoomId())
                        .opponent(chatRoom.getApplication().getApplicant()) // 상대방 구분 로직 필요 안할거같음
                        .recruitId(recruitId)
                        .fileUtil(fileUtilS3)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ChatRoom selectChatRoomById(Long chatRoomId) throws CustomException {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new CustomException(NO_CHAT_ROOM));
    }

    @Override
    public ChatRoom selectChatRoomByApplication(Application application) throws CustomException {
        return chatRoomRepository.findByApplication(application);
    }

    @Override
    public ChatRoom createChatRoom(Application application) throws CustomException {
        ChatRoom chatRoom = ChatRoom.builder()
                .application(application)
                .build();

        return chatRoomRepository.save(chatRoom);
    }

    @Transactional
    @Override
    public List<Long> getChatMemberIds(Long chatRoomId) throws CustomException {
        List<ChatMember> chatMembers = selectChatRoomById(chatRoomId).getChatMembers();
        return chatMembers.stream()
                .map(chatMember -> chatMember.getUser().getId())
                .collect(Collectors.toList());
    }
}
