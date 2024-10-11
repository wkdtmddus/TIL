package com.ssafy.ios.lineup.backend.domain.repository.chat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMessage;
import com.ssafy.ios.lineup.backend.domain.entity.QChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatMessageCustomRepositoryImpl implements ChatMessageCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChatMessage> findAllByChatRoomIdOrderByCreatedAtDesc(Long chatRoomId) {
        QChatMessage chatMessage = QChatMessage.chatMessage;

        return queryFactory
                .selectFrom(chatMessage)
                .from(chatMessage)
                .where(chatMessage.chatRoomId.eq(chatRoomId))
                .fetch();
    }

    @Override
    public ChatMessage findTopByChatRoomIdOrderByCreatedAtDesc(Long chatRoomId) {
        QChatMessage chatMessage = QChatMessage.chatMessage;

        return queryFactory
                .selectFrom(chatMessage)
                .where(chatMessage.chatRoomId.eq(chatRoomId))
                .orderBy(chatMessage.createdAt.desc())
                .fetchFirst();
    }
}
