package com.ssafy.ios.lineup.backend.domain.entity;

import com.ssafy.ios.lineup.backend.common.constant.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    //    @Builder
//    User(String email, Role role) {
//        this.email = email;
//        this.role = role;
//    }
    @Builder
    User(String email, String role) {
        this.email = email;
        this.role = role;
        this.userCash = new UserCash(this);
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true)
    private String nickname;

    @Column(length = 64)
    private String email;

    @Column(length = 64)
    private String realName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 64)
    private String profileImgFilename;

    @Column(length = 64)
    private String providerId;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    private String role;

    private LocalDate birthYear;

//    @Enumerated(EnumType.STRING)
//    private Provider provider;

    private String provider;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserCash userCash;

    public void OAuth2Update(User tempOAuthUser) {
        this.role = tempOAuthUser.getRole();
        this.nickname = tempOAuthUser.getNickname();
    }

    public void updateUserImg(String profileImgFilename) {
        this.profileImgFilename = profileImgFilename;
    }
}

