package com.ssafy.ios.lineup.backend.application.controller.user;

import com.ssafy.ios.lineup.backend.application.facade.user.UserFacade;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/users/{user-id}")
    public ResponseEntity<?> getUserProfile(@PathVariable(name = "user-id") Long userId) {
        UserProfileResponse userProfileResponse = userFacade.getUserProfileByUserId(userId);
        if (userProfileResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
    }

}
