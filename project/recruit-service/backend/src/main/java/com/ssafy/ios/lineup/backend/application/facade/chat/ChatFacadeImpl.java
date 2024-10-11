package com.ssafy.ios.lineup.backend.application.facade.chat;

import com.ssafy.ios.lineup.backend.application.service.application.ApplicationService;
import com.ssafy.ios.lineup.backend.application.service.chat.ChatMemberService;
import com.ssafy.ios.lineup.backend.application.service.chat.ChatMessageService;
import com.ssafy.ios.lineup.backend.application.service.chat.ChatRoomService;
import com.ssafy.ios.lineup.backend.application.service.chat.redis.RedisPublisher;
import com.ssafy.ios.lineup.backend.application.service.chat.redis.RedisSubscriber;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageRequest;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.dto.user.CustomUserDetails;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMember;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatFacadeImpl implements ChatFacade {

    private final ChatRoomService chatRoomService;
    private final ChatMemberService chatMemberService;
    private final ChatMessageService chatMessageService;
    private final ApplicationService applicationService;
    private final UserService userService;
    private final RedisPublisher redisPublisher;
    private final UserValidator userValidator;
    private final RedisSubscriber redisSubscriber;


    /* 지원 취소 시 채팅방 나가기 */
    @Override
    public void exitChatRoomByApplication(Application application) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        ChatRoom chatRoom = chatRoomService.selectChatRoomByApplication(application);
        // todo : 채팅방 유효성 검사?

        ChatMember chatMember = chatMemberService.selectChatMemberByChatRoomAndUser(chatRoom,
                loginUser);

        chatMemberService.exitChatRoom(chatMember);
    }

    /* 직접 나갈 때 */
    @Override
    public void exitChatRoom(Long chatRoomId) throws CustomException {
        ChatRoom chatRoom = chatRoomService.selectChatRoomById(chatRoomId);
        User loginUser = userService.selectLoginUser();

        userValidator.checkLoginUserNonNull(loginUser);

        ChatMember chatMember = chatMemberService.selectChatMemberByChatRoomAndUser(chatRoom,
                loginUser);

        chatMemberService.exitChatRoom(chatMember);
    }

    @Override
    public ChatRoom createChat(Application application) throws CustomException {

        ChatRoom chatRoom = chatRoomService.createChatRoom(application);
        chatMemberService.createChatMember(chatRoom, application);

        return chatRoom;
    }

    @Override
    public List<ChatMessageResponse> getChatMessages(Long chatRoomId) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        return chatMessageService.getAllMessages(chatRoomId, loginUser);
    }

    @Override
    public void sendChatMessage(ChatMessageRequest chatMessageRequest) throws CustomException {
        log.info("현재 스레드: {}", Thread.currentThread().getName());
        log.info("인증 정보: {}", SecurityContextHolder.getContext().getAuthentication());
        ChatMessageResponse chatMessageResponse = saveMessage(chatMessageRequest); // MySQL에 저장

        redisPublisher.publishMessage(chatMessageResponse); // 채널에 publish
        List<Long> chatMembers = chatRoomService.getChatMemberIds(chatMessageResponse.getChatRoomId());
        redisSubscriber.updateRoomList(chatMembers); // 송신인의 채팅방 리스트 갱신 요청 보내기
    }

    @Override
    public ChatMessageResponse saveMessage(ChatMessageRequest chatMessageRequest) throws CustomException {
        ChatRoom chatRoom = chatRoomService.selectChatRoomById(chatMessageRequest.getChatRoomId());
//        User loginUser = userService.selectLoginUser();
//        userValidator.checkLoginUserNonNull(loginUser);
//        User loginUser = userDetails.getUser();

        User sender = userService.selectUserById(chatMessageRequest.getSenderId());
        // 보낸 이와 로그인 유저 같은지 유효성 검사?

        ChatMember chatMember = chatMemberService.selectChatMemberByChatRoomAndUser(chatRoom,
                sender);

        return chatMessageService.createTextMessage(chatMember, chatMessageRequest.getContent());
    }

    @Override
    @Transactional
    public List<ChatRoomResponse> getChatRooms() throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        List<ChatRoomResponse> chatRooms = chatRoomService.getChatRoomsByUser(loginUser);
        return chatMessageService.putRecentMessage(chatRooms);
    }

    @Override
    public void markMessageRead(Long messageId, Long readerId) throws CustomException {
//        User loginUser = userService.selectLoginUser();
//        userValidator.checkUserNonNull(loginUser);
//        User loginUser = userService.selectUserById(readerId);

        chatMessageService.markMessageRead(messageId, readerId);
    }

    @Override
    public Long getChatRoomId(Application application) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        ChatRoom chatRoom = chatRoomService.selectChatRoomByApplication(application);
        return chatRoom.getChatRoomId();
    }

    @Override
    public Boolean isApplicant(Long chatRoomId) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        return chatRoomService.selectChatRoomById(chatRoomId).getApplication().getApplicant().equals(loginUser);
    }

    /* 이 밑에는 현재는 안쓰는 코드   */

    @Override
    public void sendTextMessage(long chatRoomId, String text) throws CustomException {
        ChatRoom chatRoom = chatRoomService.selectChatRoomById(chatRoomId);
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        ChatMember chatMember = chatMemberService.selectChatMemberByChatRoomAndUser(chatRoom,
                loginUser);

        chatMessageService.createTextMessage(chatMember, text);
    }

    @Override
    public void sendFileMessage(long chatRoomId, MultipartFile file) throws CustomException {
        ChatRoom chatRoom = chatRoomService.selectChatRoomById(chatRoomId);
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        ChatMember chatMember = chatMemberService.selectChatMemberByChatRoomAndUser(chatRoom,
                loginUser);

        chatMessageService.createFileMessage(chatMember, file);
    }
}
