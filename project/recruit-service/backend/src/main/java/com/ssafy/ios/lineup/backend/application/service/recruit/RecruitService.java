package com.ssafy.ios.lineup.backend.application.service.recruit;


import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitDetailResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitFormRequest;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitSearchFilter;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface RecruitService {

    /* 공고자가 공고 등록 */
    Recruit createRecruit(RecruitFormRequest recruit, MultipartFile recruitImg)
            throws CustomException;

    /* 공고 수정 */
    void updateRecruit(RecruitFormRequest recruitFormRequest, Long recruitId,
            MultipartFile recruitImg)
            throws CustomException;

    /* 공고 상세 페이지 */
    RecruitDetailResponse getRecruitDetail(Long recruitId) throws CustomException;

    /* 공고 삭제 */
    void deleteRecruit(Long recruitId) throws CustomException;


    /* 공고 Id 조회 */
    Recruit selectRecruitByIdAndNull(Long recruitId) throws CustomException;

    List<RecruitCardResponse> selectRecruits();

    /* 공고 기간 만료 체크 */
    void updateExpiredRecruits();

    /* 작성자에 의한 공고 상태 변경 */
    void updateRecruitStatus(Long recruitId, RecruitStatus recruitStatus) throws CustomException;

    /* 공고에 대한 좋아요 및 취소 */
    void toggleLikeRecruit(Long recruitId, User user) throws CustomException;

    /* 공고에 대한 좋아요 개수 */
    Long getLikeCount(Long recruitId) throws CustomException;

    List<RecruitCardResponse> searchRecruitByFilter(RecruitSearchFilter recruitSearchFilter);


    /* 공고가 시작되었는지 확인 */
    void checkRecruitStarted(Recruit recruit) throws CustomException;
}
