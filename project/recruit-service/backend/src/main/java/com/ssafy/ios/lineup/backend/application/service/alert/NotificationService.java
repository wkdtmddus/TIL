package com.ssafy.ios.lineup.backend.application.service.alert;

import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    // 메시지 알림
    SseEmitter subscribe(User user);

    // 계약 알림 - 피계약자에게
    void notifyContract(Long contractId);

    void notifyTest(User user);

    /*
     *  을에게 계약 요청 알람 보내기
     * */
    void notifyContractRequestReceive(ContractRequest contractRequest);

    // 알림 삭제
    boolean deleteContractNotification(Long contractId);

    void notifyContractRequestAccept(ContractRequest contractRequest);
}
