package com.ssafy.ios.lineup.backend.domain.repository.chat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ios.lineup.backend.domain.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChatRoom> findByRecruitId(Long recruitId) {
        QChatRoom chatRoom = QChatRoom.chatRoom;

        return queryFactory
                .selectFrom(chatRoom)
                .from(chatRoom)
                .where(chatRoom.application.recruit.id.eq(recruitId))
                .fetch();
    }

    @Override
    public List<ChatRoom> findByUser(User user) {
        QChatRoom chatRoom = QChatRoom.chatRoom;
        QChatMember chatMember = QChatMember.chatMember;
        QUser userEntity = QUser.user;

        return queryFactory
                .select(chatRoom)
                .from(chatRoom)
                .join(chatRoom.chatMembers, chatMember).fetchJoin()
                .join(chatMember.user, userEntity).fetchJoin()
                .where(chatMember.user.eq(user))
                .fetch();

        /* 나간 채팅방은 안갖고 오는 쿼리 */
//        return queryFactory
//                .selectFrom(chatRoom)
//                .join(chatRoom.chatMembers, chatMember)
//                .where(chatMember.user.eq(user)
//                        .and(chatMember.enterAt.isNotNull()))
//                .fetch();
    }
}
