package com.ssafy.whoareyou.user.entity;

import com.ssafy.whoareyou.user.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "gender", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

//    @Column(nullable = false, length = 20)
//    private String gender;

    @Column(length = 10)
    private String type; //"general", "kakao", "naver"

    @Column(columnDefinition = "integer default 0")
    private Integer matchingCount;

    @Column(columnDefinition = "integer default 0")
    private Integer successCount;

    @Column(length = 255)
    private String refreshToken;

    public User(SignUpRequestDto dto){
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.matchingCount = 0;
        this.successCount = 0;
        this.type = "general";
    }

    public User(String email, String name, String nickname, String type){
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.matchingCount = 0;
        this.successCount = 0;
        this.type = type;
    }

    public void increaseMatchingCount(){
        matchingCount++;
    }
    public void increaseSuccessCount(){ successCount++; }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
