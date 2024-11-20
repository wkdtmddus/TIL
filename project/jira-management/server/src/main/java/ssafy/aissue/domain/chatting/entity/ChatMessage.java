package ssafy.aissue.domain.chatting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ssafy.aissue.domain.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatting_id")
    private Chatting chatting;

    private Long memberId;

    @Column(nullable = false)
    private String memberName;

    private String message;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;
}
