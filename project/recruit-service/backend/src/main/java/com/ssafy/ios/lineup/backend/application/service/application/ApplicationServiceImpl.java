package com.ssafy.ios.lineup.backend.application.service.application;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.ApplicationErrorCode.NO_APPLICANT_INFO;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.dto.application.ApplicantCardResponse;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.repository.application.ApplicationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final FileUtil fileUtilS3;

    @Override
    public Application selectApplicationById(long applicationId) throws CustomException {
        return applicationRepository.findByIdAndDeletedAtNull(applicationId)
                .orElseThrow(() -> new CustomException(NO_APPLICANT_INFO));
    }

    /* 특정 공고에 대한 지원자 리스트 조회 */
    @Override
    public List<ApplicantCardResponse> getApplicantsByRecruitId(Long recruitId)
            throws CustomException {
        List<Application> applications = applicationRepository.findByRecruitId(recruitId);

        return applications.stream()
                .map(application -> ApplicantCardResponse.builder()
                        .applicantId(application.getApplicant().getId())
                        .nickname(application.getApplicant().getNickname())
                        .profileImg(application.getApplicant().getProfileImgFilename())
                        .createdAt(application.getCreatedAt())
                        .chatRoomId(application.getChatRoom().getChatRoomId())
                        .fileUtil(fileUtilS3)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Long createApplication(Application application)
            throws CustomException {
        Application app = applicationRepository.save(application);
        return app.getId();
    }

    @Override
    public Application deleteApplication(Application application) throws CustomException {
        application.updateDeletedAt();
        return applicationRepository.save(application);
    }

    @Override
    public Long setChatRoom(Application application, ChatRoom chatRoom) throws CustomException {
        application.setChatRoom(chatRoom);
        System.out.println(
                "save됌 : ApplicationServiceImpl - setChatRoom " + chatRoom.getChatRoomId());
        applicationRepository.save(application);
        return chatRoom.getChatRoomId();
    }

    @Override
    public Application findApplication(Long recruitId, Long applicantId) {
        return applicationRepository.findByRecruitIdAndApplicantId(recruitId, applicantId)
                .orElse(null);
    }
}
