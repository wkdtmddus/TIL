package com.ssafy.ios.lineup.backend.application.service.recruit;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.RecruitErrorCode.ALREADY_STARTED_RECRUIT;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.RecruitErrorCode.NO_RECRUIT;

import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.DateTimeUtil;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitDetailResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitFormRequest;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitSearchFilter;
import com.ssafy.ios.lineup.backend.domain.entity.LikeRecruit;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.recruit.LikeRecruitRepository;
import com.ssafy.ios.lineup.backend.domain.repository.recruit.RecruitRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;
    private final LikeRecruitRepository likeRecruitRepository;
    private final FileUtil fileUtil;
    private final DateTimeUtil dateTimeUtil;

    @Override
    public Recruit createRecruit(RecruitFormRequest recruitFormRequest, MultipartFile recruitImg)
            throws CustomException {
        Recruit recruit = Recruit.builder()
                .recruitFormRequest(recruitFormRequest)
                .build();

        /* 날짜와 시간을 LocalDateTime으로 변경 */
        updateRecruitDatesAndTimes(recruit, recruitFormRequest);

        /* 공고 사진을 등록한 경우 */
        if (recruitImg != null && !recruitImg.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            fileUtil.saveRecruitImg(recruitImg, uuid); // 공고 사진 저장

            recruit.updateRecruitImgFilename(
                    uuid + "." + fileUtil.getExtension(recruitImg));
        }

        return recruitRepository.save(recruit);
    }

    @Override
    public void updateRecruit(RecruitFormRequest recruitFormRequest, Long recruitId,
            MultipartFile recruitImg)
            throws CustomException {
        Recruit recruit = this.selectRecruitByIdAndNull(recruitId);

        /* 텍스트 데이터가 수정될 경우, 텍스트 데이터와 날짜 데이터를 수정 */
        if (recruitFormRequest != null) {
            recruit.updateRecruit(recruitFormRequest);
            updateRecruitDatesAndTimes(recruit, recruitFormRequest);
        }
        if (recruitFormRequest.getStartAt() != null && !recruitFormRequest.getStartDate()
                .isEmpty()) {
            LocalDateTime startAt = DateTimeUtil.parseDateTime(recruitFormRequest.getStartDate(),
                    recruitFormRequest.getStartAt());
            recruit.updateRecruitStartDate(startAt);
        }

        //TODO: 수정 필요?
//        if (recruitFormRequest.getEndAt() != null && )

        /* 새로운 이미지가 업로드된 경우 */
//        if (recruitImg != null && !recruitImg.isEmpty()) {
//            /* 기존의 이미지가 존재한다면 해당 이미지를 삭제 */
//            if (recruit.getRecruitImg() != null) {
//                fileUtil.deleteFileImg(recruit.getRecruitImg());
//            }
//
//            /* 공고 사진 저장 */
//            String uuid = UUID.randomUUID().toString();
//            fileUtil.saveRecruitImg(recruitImg, uuid);
//            recruit.updateRecruitImgFilename(
//                    uuid + "." + fileUtil.getExtension(recruitImg));
//        }
//
//        // 파일을 빈 값으로 보내준 경우, 기존에 있는 값을 삭제해준다.
//        if (recruit.getRecruitImg() != null && recruitImg == null) {
//            fileUtil.deleteFileImg(recruit.getRecruitImg());
//            // 기존에 있는 파일 이름을 삭제한다.
//            recruit.updateRecruitImgFilename(null);
//        }
//
//        // 변경 감지를 통해서 공고 다시 저장
//        recruitRepository.save(recruit);

        // 이미지 수정 로직
        if (recruitImg != null) {
            if (!recruitImg.isEmpty()) {
                // 새로운 이미지가 업로드된 경우, 기존 이미지 삭제 후 새로운 이미지 등록
                if (recruit.getRecruitImg() != null) {
                    log.info("Deleting existing image: {}", recruit.getRecruitImg());
                    fileUtil.deleteFileImg(recruit.getRecruitImg());
                }

                // 새로운 이미지 저장
                String uuid = UUID.randomUUID().toString();
                fileUtil.saveRecruitImg(recruitImg, uuid);
                recruit.updateRecruitImgFilename(uuid + "." + fileUtil.getExtension(recruitImg));
                log.info("New image file saved with UUID: {}", uuid);
            } else {
                // recruitImg가 비어 있는 경우 (이미지 업데이트가 아닌 삭제 요청일 수 있음)
                if (recruit.getRecruitImg() != null) {
                    log.info("Deleting image because empty file was provided: {}",
                            recruit.getRecruitImg());
                    fileUtil.deleteFileImg(recruit.getRecruitImg());
                    recruit.updateRecruitImgFilename(null);
                }
            }
        }

        // recruitImg가 null일 경우, 기존 이미지를 삭제
        if (recruit.getRecruitImg() != null && recruitImg == null) {
            log.info("Deleting image because no new file was provided (null): {}",
                    recruit.getRecruitImg());
            fileUtil.deleteFileImg(recruit.getRecruitImg());
            recruit.updateRecruitImgFilename(null);
        }

        // 변경된 공고 정보를 저장
        recruitRepository.save(recruit);
    }

    private void updateRecruitDatesAndTimes(Recruit recruit,
            RecruitFormRequest recruitFormRequest) {
        String startDateStr = recruitFormRequest.getStartDate();
        String startTimeStr = recruitFormRequest.getStartAt();
        String endDateStr = recruitFormRequest.getEndDate();
        String endTimeStr = recruitFormRequest.getEndAt();

        // 시작 날짜와 시간 업데이트
        if (startDateStr != null && !startDateStr.isEmpty()) {
            LocalDateTime startDate = dateTimeUtil.parseDateTime(startDateStr, startTimeStr);
            recruit.updateRecruitStartDate(startDate);
        }

        // 종료 날짜와 시간 업데이트
        if (endDateStr != null && !endDateStr.isEmpty()) {
            LocalDateTime endDate = dateTimeUtil.parseDateTime(endDateStr, endTimeStr);
            recruit.updateRecruitEndDate(endDate);
        }
    }

    @Override
    public RecruitDetailResponse getRecruitDetail(Long recruitId) throws CustomException {
        Recruit recruit = recruitRepository.findByIdAndDeletedAtNull(recruitId)
                .orElseThrow(() -> new CustomException(NO_RECRUIT));

        // 조회수 증가
        recruit.updateViewCount(recruit.getViewCount() + 1);
        recruitRepository.save(recruit);

        return RecruitDetailResponse.builder()
                .id(recruit.getId())
                .writerId(recruit.getWriter().getId())
                .writerNickname(recruit.getWriter().getNickname())
                .writerProfileImg(
                        fileUtil.getUserProfileImg(recruit.getWriter().getProfileImgFilename()))
                .title(recruit.getTitle())
                .content(recruit.getContent())
                .recruitImg(fileUtil.getRecruitImg(recruit.getRecruitImg()))
                .placeType(recruit.getPlaceType())  // 변환된 한글 값 사용
                .recruitStatus(recruit.getRecruitStatus())
                .serviceType(recruit.getServiceType())
                .startAt(recruit.getStartAt())
                .endAt(recruit.getEndAt())
                .location(recruit.getLocation())
                .successSalary(recruit.getSuccessSalary())
                .failSalary(recruit.getFailSalary())
                .contracteeDeposit(recruit.getContracteeDeposit())
                .createdAt(recruit.getCreatedAt())
                .modifiedAt(recruit.getModifiedAt())
                .build();
    }

    @Override
    public void deleteRecruit(Long recruitId) throws CustomException {
        Recruit recruit = this.selectRecruitByIdAndNull(recruitId);

        /* 공고 사진 있다면 삭제 */
        if (recruit.getRecruitImg() != null && !recruit.getRecruitImg().isEmpty()) {
            fileUtil.deleteFileImg(recruit.getRecruitImg());
        }

        recruit.updateDeletedAt();
        recruitRepository.save(recruit);
    }

    @Override
    public Recruit selectRecruitByIdAndNull(Long recruitId) throws CustomException {
        return recruitRepository.findByIdAndDeletedAtNull(recruitId)
                .orElseThrow(() -> new CustomException(NO_RECRUIT));
    }

    @Override
    public List<RecruitCardResponse> selectRecruits() {
        return recruitRepository.getRecruits();
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateExpiredRecruits() {
        LocalDateTime now = LocalDateTime.now();

        // 현재 시간을 기준으로 기간이 만료된 공고 조회 및 상태 업데이트
        List<Recruit> expiredRecruits = recruitRepository.findExpiredRecruits(now);
        expiredRecruits.forEach(recruit -> recruit.updateRecruitStatus(RecruitStatus.EXPIRED));

        // 상태 업데이트 후 DB에 저장
        recruitRepository.saveAll(expiredRecruits);
    }

    @Override
    public void updateRecruitStatus(Long recruitId, RecruitStatus recruitStatus) {
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new CustomException(NO_RECRUIT));

        recruit.updateRecruitStatus(recruitStatus);
    }

    @Override
    public void toggleLikeRecruit(Long recruitId, User loginUser) throws CustomException {
        Recruit recruit = recruitRepository.findByIdAndDeletedAtNull(recruitId)
                .orElseThrow(() -> new CustomException(NO_RECRUIT));

        boolean alreadyLiked = likeRecruitRepository.existsByRecruitIdAndUser(recruit.getId(),
                loginUser);

        if (alreadyLiked) {
            // 이미 좋아요를 진행한 경우, 해당 공고에 대한 좋아요 횟수 감소
            recruit.updateLikeCount(recruit.getLikeCount() - 1);
            likeRecruitRepository.deleteByRecruitId(recruitId);
        } else {
            // 좋아요를 진행하지 않은 경우, 해당 공고에 대한 좋아요 횟수 추가
            LikeRecruit likeRecruit = LikeRecruit.builder().recruit(recruit).user(loginUser)
                    .build();
            recruit.updateLikeCount(recruit.getLikeCount() + 1);
            likeRecruitRepository.save(likeRecruit);
        }

    }

    @Override
    public Long getLikeCount(Long recruitId) throws CustomException {
        return likeRecruitRepository.countByRecruitId(recruitId);
    }


    @Override
    public List<RecruitCardResponse> searchRecruitByFilter(
            RecruitSearchFilter recruitSearchFilter) {
        return recruitRepository.searchRecruitByFilter(recruitSearchFilter);
    }

    @Override
    public void checkRecruitStarted(Recruit recruit) throws CustomException {
        // 현재 시각이 공고 시작 이후이면
        if (LocalDateTime.now().isAfter(recruit.getStartAt())) {
            throw new CustomException(ALREADY_STARTED_RECRUIT);
        }

    }

    private String generateRecruitImgUrl(String recruitImg) {
        if (recruitImg == null || recruitImg.isEmpty()) {
            return null;
        }

        // 파일 경로를 URL 경로로 변환
        return "/images/recruits/" + recruitImg; // 기본 URL 경로 + 이미지 파일명
    }

}
