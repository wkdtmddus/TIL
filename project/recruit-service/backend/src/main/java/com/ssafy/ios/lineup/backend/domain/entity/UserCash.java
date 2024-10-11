package com.ssafy.ios.lineup.backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCash {

    @Id
    @Column(name = "user_cash_id")
    private Long id;

    //    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private int cash = 0;

    @Builder
    public UserCash(User user) {
        this.user = user;
    }


    public void updateCash(int updatedCash) {
        this.cash = updatedCash;
    }
}

