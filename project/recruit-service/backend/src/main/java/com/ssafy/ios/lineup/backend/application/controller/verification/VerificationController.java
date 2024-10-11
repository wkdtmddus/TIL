package com.ssafy.ios.lineup.backend.application.controller.verification;

import com.ssafy.ios.lineup.backend.application.facade.Verification.VerificationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/verifications")
public class VerificationController {

    private final VerificationFacade verificationFacade;

}
