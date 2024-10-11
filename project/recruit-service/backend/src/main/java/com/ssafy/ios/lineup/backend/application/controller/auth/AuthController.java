package com.ssafy.ios.lineup.backend.application.controller.auth;

import com.ssafy.ios.lineup.backend.application.facade.auth.AuthFacade;
import com.ssafy.ios.lineup.backend.application.service.auth.AuthService;
import com.ssafy.ios.lineup.backend.application.service.pay.UserCashService;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.util.JWTUtil;
import com.ssafy.ios.lineup.backend.domain.dto.user.OAuth2SignUpRequest;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserDetailResponse;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthFacade authFacade;
    private final JWTUtil jwtUtil;
    private final AuthService authService;
    private final UserService userService;
    private final UserCashService userCashService;

    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        UserDetailResponse userDetailDto = authService.getUserInfo(request);
        if (userDetailDto != null) {
            return new ResponseEntity<>(userDetailDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/users")
    public ResponseEntity<?> register(
            @RequestPart("oauth2SignupRequest") OAuth2SignUpRequest oAuth2SignUpRequest,
            MultipartFile userImg, HttpServletResponse response) {
        if (userImg == null || userImg.isEmpty()) {
            log.error("Received null or empty MultipartFile for userImg.");
            return new ResponseEntity<>("Image file is missing", HttpStatus.BAD_REQUEST);
        }

        Long saveId = authService.saveOAuth2User(oAuth2SignUpRequest, userImg);

        log.info("유저 회원가입이 완료되었습니다, 유저 아이디 : {}", saveId);

        User signupUser = userService.selectUserById(saveId);

        String accessToken = jwtUtil.createOauthJwt(signupUser.getEmail(), signupUser.getRole());
        String refreshToken = jwtUtil.createRefreshJwt(signupUser.getEmail(), signupUser.getRole());

        // 어차피 소문자로 감.
        response.setHeader("Authorization", accessToken);   // accessToken을 헤더에 추가
        response.setHeader("refreshtoken", refreshToken); // refreshToken을 헤더에 추가

        return new ResponseEntity<>(saveId, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> getAccessToken(HttpServletRequest request,
            HttpServletResponse response) {
        String accessToken = authService.getAccessToken(request, response);
        if (accessToken != null) {
            response.addHeader("Authorization", "Bearer " + accessToken);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Refresh token이 없습니다.", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<?> requestAccess(HttpServletRequest request,
            HttpServletResponse response) {
        authService.requestAccess(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<?> checkNickname(@RequestParam String nickname) {
        boolean isDuplicate = authService.isNicknameDuplicate(nickname);
        return new ResponseEntity<>(isDuplicate, HttpStatus.OK);
    }
}
