package com.ssafy.ios.lineup.backend.domain.entity;

import com.ssafy.ios.lineup.backend.common.constant.ContentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_member_id")
//    private ChatMember chatMember;

    @Column
    private Long senderId;

    @Column
    private Long chatRoomId;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(length = 6990555)
    private String content;

    @Column
    private Boolean isRead;

    @Builder
    public ChatMessage(ChatMember chatMember, ContentType contentType, String content) {
        this.senderId = chatMember.getUser().getId();
        this.chatRoomId = chatMember.getChatRoom().getChatRoomId();
        this.contentType = contentType;
        this.content = content;
        this.isRead = false;
    }

    public void readMessage() {
        this.isRead = true;
    }
}