package com.ssafy.ios.lineup.backend.common.aop;

import com.ssafy.ios.lineup.backend.application.service.pay.TransferDetailService;
import com.ssafy.ios.lineup.backend.application.service.pay.UserCashService;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SyncCashAspect {
    
    private final TransferDetailService transferDetailService;
    private final UserCashService userCashService;

    @After("@annotation(com.ssafy.ios.lineup.backend.common.aop.SyncCash) && args(loginUser,..)")
    public void beforeSyncCashMethod(User loginUser) throws Throwable {

        int updatedCash = transferDetailService.calculateUserCash(loginUser);
        userCashService.updateCashByUserAndUpdatedCash(loginUser, updatedCash);
    }
}