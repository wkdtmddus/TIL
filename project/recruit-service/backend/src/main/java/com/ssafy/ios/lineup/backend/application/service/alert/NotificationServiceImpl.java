package com.ssafy.ios.lineup.backend.application.service.alert;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.ContractErrorCode.NO_CONTRACT;

import com.ssafy.ios.lineup.backend.application.controller.alert.NotificationController;
import com.ssafy.ios.lineup.backend.application.controller.auth.AuthController;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Notification;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.alert.NotificationRepository;
import com.ssafy.ios.lineup.backend.domain.repository.contract.ContractRepository;
import com.ssafy.ios.lineup.backend.domain.repository.contract_request.ContractRequestRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final Map<Long, Integer> notificationCounts = new ConcurrentHashMap<>();     // 알림 개수 저장
    private final ContractRequestRepository contractRequestRepository;
    private final NotificationRepository notificationRepository;
    private final ContractRepository contractRepository;
    private final AuthController authController;

    // 메시지 알림
    @Override
    public SseEmitter subscribe(User user) {
        // 현재 클라이언트를 위한 sseEmitter 생성
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try { // 연결
            sseEmitter.send(SseEmitter.event().name("CONNECT"));
        } catch (IOException e) {
            log.debug(e.getMessage());
        }

        // user 의 pk 값을 key 값으로 해서 sseEmitter 를 저장
        NotificationController.sseEmitters.put(user.getId(), sseEmitter);

        sseEmitter.onCompletion(() -> NotificationController.sseEmitters.remove(user.getId()));
        sseEmitter.onTimeout(() -> NotificationController.sseEmitters.remove(user.getId()));
        sseEmitter.onError((e) -> NotificationController.sseEmitters.remove(user.getId()));

        return sseEmitter;
    }

    @Override
    public void notifyTest(User user) {

        SseEmitter sseEmitter = NotificationController.sseEmitters.get(user.getId());
        try {
            Map<String, String> eventData = new HashMap<>();
            eventData.put("message", "계약이 체결되었습니다");
            eventData.put("senderNickname", "SYSTEM");
            eventData.put("contents", "알람 TEST");
            eventData.put("createdAt", LocalDateTime.now().toString());

            sseEmitter.send(SseEmitter.event().name("ALERT_TEST").data(eventData));

            // DB 저장
            Notification notification = Notification.builder()
                    .sender(user)
                    .receiver(user)
                    .contents("알람 TEST")
                    .createdAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);

            // 알림 개수 증가
            notificationCounts.put(user.getId(),
                    notificationCounts.getOrDefault(user.getId(), 0) + 1);

            // 현재 알림 개수 전송
            sseEmitter.send(SseEmitter.event().name("NOTIFICATION_COUNT")
                    .data(notificationCounts.get(user.getId())));

        } catch (IOException e) {
            NotificationController.sseEmitters.remove(user.getId());
        }
    }


    // 계약 알림 - 피계약자에게
    @Override
    public void notifyContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(
                () -> new CustomException(NO_CONTRACT)
        );

        User contractor = contract.getRecruit().getWriter();
        User contractee = contract.getContractee();
        LocalDateTime contractCreatedAt = contract.getCreatedAt();
        String contents = String.format("%s에 %s님과 공고(%s) 계약",
                contractCreatedAt.toString(),
                contractor.getNickname(),
                contract.getRecruit().getTitle());

        if (!NotificationController.sseEmitters.containsKey(contractee.getId())) {
            return;
        }

        SseEmitter sseEmitter = NotificationController.sseEmitters.get(contractee.getId());
        try {
            Map<String, String> eventData = new HashMap<>();
            eventData.put("message", "계약이 체결되었습니다");
            eventData.put("senderNickname", contractor.getNickname());
            eventData.put("contents", contents);
            eventData.put("createdAt", contractCreatedAt.toString());

            sseEmitter.send(SseEmitter.event().name("CONTRACT_SUCCESS").data(eventData));

            // DB 저장
            Notification notification = Notification.builder()
                    .sender(contractor)
                    .receiver(contractee)
                    .contents(contents)
                    .createdAt(contractCreatedAt)
                    .build();
            notificationRepository.save(notification);

            // 알림 개수 증가
            notificationCounts.put(contractee.getId(),
                    notificationCounts.getOrDefault(contractee.getId(), 0) + 1);

            // 현재 알림 개수 전송
            sseEmitter.send(SseEmitter.event().name("NOTIFICATION_COUNT")
                    .data(notificationCounts.get(contractee.getId())));

        } catch (IOException e) {
            NotificationController.sseEmitters.remove(contractee.getId());
        }
    }

    /*
     *  을에게 계약 요청 알람 보내기
     * */
    @Override
    public void notifyContractRequestReceive(ContractRequest contractRequest) {

        User applicant = contractRequest.getApplication().getApplicant();
        User writer = contractRequest.getApplication().getRecruit().getWriter();
        LocalDateTime contractCreatedAt = applicant.getCreatedAt();

        String contents = String.format("%s에 %s님에게서 공고(%s) 계약 요청",
                contractCreatedAt.toString(),
                writer,
                contractRequest.getApplication().getRecruit().getTitle());

        if (!NotificationController.sseEmitters.containsKey(applicant.getId())) {
            return;
        }

        SseEmitter sseEmitter = NotificationController.sseEmitters.get(applicant.getId());
        try {
            Map<String, String> eventData = new HashMap<>();
            eventData.put("message", "계약 요청이 왔습니다.");
            eventData.put("senderNickname", writer.getNickname());
            eventData.put("contents", contents);
            eventData.put("createdAt", contractCreatedAt.toString());

            sseEmitter.send(SseEmitter.event().name("CONTRACT_REQUEST_RECEIVE").data(eventData));

            // DB 저장
            Notification notification = Notification.builder()
                    .sender(writer)
                    .receiver(applicant)
                    .contents(contents)
                    .createdAt(contractCreatedAt)
                    .build();
            notificationRepository.save(notification);

            // 알림 개수 증가
            notificationCounts.put(applicant.getId(),
                    notificationCounts.getOrDefault(applicant.getId(), 0) + 1);

            // 현재 알림 개수 전송
            sseEmitter.send(SseEmitter.event().name("NOTIFICATION_COUNT")
                    .data(notificationCounts.get(applicant.getId())));

        } catch (IOException e) {
            NotificationController.sseEmitters.remove(applicant.getId());
        }
    }


    // 알림 삭제
    @Override
    public boolean deleteContractNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        // TODO : Throw CustomException

        notificationRepository.delete(notification);

        // 알림 개수 감소
        if (notificationCounts.containsKey(notification.getId())) {
            int currentCount = notificationCounts.get(notification.getReceiver().getId());
            if (currentCount > 0) {
                notificationCounts.put(notification.getReceiver().getId(), currentCount - 1);
            }
        }

        // 현재 알림 개수 전송
        SseEmitter sseEmitter = NotificationController.sseEmitters.get(
                notification.getReceiver().getId());
        try {
            sseEmitter.send(
                    SseEmitter.event().name("NOTIFICATION_COUNT")
                            .data(notificationCounts.get(notification.getReceiver().getId())));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return true;
    }

    /*
     *  을이 계약 요청 수락 한 거 갑에게 전송
     * */
    @Override
    public void notifyContractRequestAccept(ContractRequest contractRequest) {
        User applicant = contractRequest.getApplication().getApplicant();
        User writer = contractRequest.getApplication().getRecruit().getWriter();
        LocalDateTime contractCreatedAt = applicant.getCreatedAt();

        String contents = String.format("%s님이 공고(%s) 계약 요청 수락",
                applicant,
                contractRequest.getApplication().getRecruit().getTitle());

        if (!NotificationController.sseEmitters.containsKey(writer.getId())) {
            return;
        }

        SseEmitter sseEmitter = NotificationController.sseEmitters.get(applicant.getId());
        try {
            Map<String, String> eventData = new HashMap<>();
            eventData.put("message", "계약 요청이 왔습니다.");
            eventData.put("senderNickname", applicant.getNickname());
            eventData.put("contents", contents);
            eventData.put("createdAt", contractCreatedAt.toString());

            sseEmitter.send(SseEmitter.event().name("CONTRACT_REQUEST_ACCEPT").data(eventData));

            // DB 저장
            Notification notification = Notification.builder()
                    .sender(applicant)
                    .receiver(writer)
                    .contents(contents)
                    .createdAt(contractCreatedAt)
                    .build();
            notificationRepository.save(notification);

            // 알림 개수 증가
            notificationCounts.put(applicant.getId(),
                    notificationCounts.getOrDefault(writer.getId(), 0) + 1);

            // 현재 알림 개수 전송
            sseEmitter.send(SseEmitter.event().name("NOTIFICATION_COUNT")
                    .data(notificationCounts.get(writer.getId())));

        } catch (IOException e) {
            NotificationController.sseEmitters.remove(writer.getId());
        }
    }
}
