DROP SCHEMA IF EXISTS lineup;
CREATE SCHEMA if NOT EXISTS lineup DEFAULT CHARACTER SET UTF8mb4;
USE lineup;

-- 테이블 생성
CREATE TABLE IF NOT EXISTS user
(
    user_id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname             VARCHAR(64)             NOT NULL,
    email                VARCHAR(64)             NOT NULL,
    real_name            VARCHAR(64)             NOT NULL,
    gender               ENUM ('MALE', 'FEMALE') NOT NULL,
    profile_img_filename VARCHAR(64),
    provider_id          VARCHAR(64),
    role                 VARCHAR(64),
    birth_year           DATETIME,
    provider             VARCHAR(16)
);

INSERT INTO user (user_id, nickname, email, real_name, gender, profile_img_filename,
                  provider_id, role, birth_year, provider)
VALUES (1, '뭉기', 'c880910@example.com', '정문기', 'FEMALE', null, 'provider_12345', 'USER',
        '1997-09-10 00:00:00', 'GOOGLE'),
       (2, '재쪽', 'jaeyoung@example.com', '정재영', 'MALE', null, 'provider_12222', 'USER',
        '1997-09-10 00:00:00', 'KAKAO'),
       (3, '중궈어러', 'ndk@example.com', '남동균', 'MALE', null, 'provider_12343', 'USER',
        '1997-09-10 00:00:00', 'NAVER');