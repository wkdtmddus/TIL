package com.ssafy.ios.lineup.backend.domain.entity;

import com.ssafy.ios.lineup.backend.common.constant.ServiceType;
import com.ssafy.ios.lineup.backend.common.constant.VerificationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class Verification extends BaseEntity {
    @Builder
    public Verification(VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Enumerated(EnumType.STRING)
    private VerificationType verificationType;

    private String photoUrl;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean verified; // 퇴근 인증

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean photoVerified; // 사진 인증

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean GPSVerified; // GPS 인증

    // 출근 인증용 필드
    private Float latitude; // 대행자 위도
    private Float longitude; // 대행자 경도

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    // 퇴근 인증용 필드
    private String qrCodeContent; // 공고자가 생성한 QR 코드 내용

    public void updatePhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void updateVerificationType(VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    public void updateLocation(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateQrCodeContent(String qrCodeContent) {
        this.qrCodeContent = qrCodeContent;
    }

    public boolean isGPSVerified() {
        return GPSVerified;
    }

    public boolean isPhotoVerified() {
        return photoVerified;
    }

    public boolean isVerified() {
        return verified;
    }

    public void updateVerified(boolean verified) {
        this.verified = verified;
    }
    public void updatePhotoVerified(boolean photoVerified) {
        this.photoVerified = photoVerified;
    }

    public void updateGPSVerified(boolean GPSVerified) {
        this.GPSVerified = GPSVerified;
    }
}
