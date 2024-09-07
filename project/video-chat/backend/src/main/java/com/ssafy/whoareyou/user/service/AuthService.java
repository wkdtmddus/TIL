package com.ssafy.whoareyou.user.service;

import com.ssafy.whoareyou.user.dto.request.auth.*;
import com.ssafy.whoareyou.user.dto.response.UserResponseDto;
import com.ssafy.whoareyou.user.dto.response.auth.*;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super EmailCheckResponseDto> emailCheck(EmailCheckRequestDto dto);
    ResponseEntity<? super NicknameCheckResponseDto> nicknameCheck(NicknameCheckRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp (SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto>  signIn (SignInRequestDto dto);
    ResponseEntity<? super LogoutResponseDto> logout(LogoutRequestDto dto);
    UserResponseDto getUserById(int id);
}
