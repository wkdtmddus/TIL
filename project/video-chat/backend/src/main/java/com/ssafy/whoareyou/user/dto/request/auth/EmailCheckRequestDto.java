package com.ssafy.whoareyou.user.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailCheckRequestDto {

    @NotBlank
    private String email;
}
