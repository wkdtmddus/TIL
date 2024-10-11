package com.ssafy.ios.lineup.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"recruit_id", "applicant_id"})
        }
)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Builder
    public Application(Recruit recruit, User applicant) {
        this.recruit = recruit;
        this.applicant = applicant;
    }
}
