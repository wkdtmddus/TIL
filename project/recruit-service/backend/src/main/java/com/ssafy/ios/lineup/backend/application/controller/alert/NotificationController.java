package com.ssafy.ios.lineup.backend.application.controller.alert;


import com.ssafy.ios.lineup.backend.application.service.alert.NotificationService;
import com.ssafy.ios.lineup.backend.application.service.auth.AuthService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final AuthService authService;
    private final NotificationService notificationService;
    public static final Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    // 알림
    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        return notificationService.subscribe(authService.getLoginUser());
    }

    @GetMapping("/receive-test")
    public ResponseEntity<?> receiveTest() {
        notificationService.notifyTest(authService.getLoginUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 알림 삭제
    @DeleteMapping("/delete/{notification-id}")
    public ResponseEntity<?> deleteNotification(
            @PathVariable("notification-id") Long notificationId) {
        return new ResponseEntity<>(notificationService.deleteContractNotification(notificationId),
                HttpStatus.OK);
    }

}
