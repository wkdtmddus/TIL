package com.ssafy.ios.lineup.backend.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.user fileName       :
 * OAuth2SignUpRequest author         : moongi date           : 9/23/24 description    :
 */
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2SignUpRequest {

    @NotNull
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$")
    private String email;

    @NotNull
    @Size(min = 2, max = 20)
    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthDate;

}
