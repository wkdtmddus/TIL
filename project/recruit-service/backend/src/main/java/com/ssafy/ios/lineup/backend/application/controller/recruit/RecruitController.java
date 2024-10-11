package com.ssafy.ios.lineup.backend.application.controller.recruit;

import com.ssafy.ios.lineup.backend.application.facade.recruit.RecruitFacade;
import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.domain.dto.application.ApplicantCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitDetailResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitFormRequest;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitSearchFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName    : com.ssafy.ios.lineup.backend.application.controller.recruit fileName       :
 * RecruitController author         : moongi date           : 9/16/24 description    :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruits")
public class RecruitController {

    private final RecruitFacade recruitFacade;

    /* 공고 생성 */
    @PostMapping
    public ResponseEntity<?> createRecruit(
            @RequestPart("recruitFormRequest") RecruitFormRequest recruitFormRequest,
            @RequestPart(value = "recruitImg", required = false) MultipartFile recruitImg) {

        // TODO: 공고 등록 시, 패스 인증 여부 확인
        return new ResponseEntity<>(recruitFacade.registerRecruit(recruitFormRequest, recruitImg),
                HttpStatus.CREATED);
    }

    /* 공고 수정 */
    @PostMapping("/{recruit-id}")
    public ResponseEntity<?> updateRecruit(
            @RequestPart(value = "recruitFormRequest", required = false) RecruitFormRequest recruitFormRequest,
            @PathVariable("recruit-id") Long recruitId,
            @RequestPart(value = "recruitImg", required = false) MultipartFile recruitImg) {

        return new ResponseEntity<>(
                recruitFacade.changedRecruit(recruitId, recruitFormRequest, recruitImg),
                HttpStatus.OK);
    }

    /* 공고 삭제 */
    @DeleteMapping("/{recruit-id}")
    public ResponseEntity<?> deleteRecruit(@PathVariable("recruit-id") Long recruitId) {

        return new ResponseEntity<>(recruitFacade.deleteRecruit(recruitId), HttpStatus.OK);
    }

    /* 공고 상세 페이지 */
    @GetMapping("/{recruit-id}")
    public ResponseEntity<?> getRecruitDetail(@PathVariable("recruit-id") Long recruitId) {
        RecruitDetailResponse recruitDetailResponse = recruitFacade.selectRecruitDetail(
                recruitId);
        return new ResponseEntity<>(recruitDetailResponse, HttpStatus.OK);
    }

    /* 자신이 작성한 공고들의 상태 변경(구인 중, 구인 완료) */
    @PostMapping("/{recruit-id}/status/{recruit-status}")
    public ResponseEntity<?> updateRecruitStatus(@PathVariable("recruit-id") Long recruitId,
                                                 @PathVariable("recruit-status") RecruitStatus recruitStatus) {
        return new ResponseEntity<>(recruitFacade.changeRecruitStatus(recruitId, recruitStatus),
                HttpStatus.OK);
    }

    /* 공고 지원자 리스트 조회 */
    @GetMapping("/{recruit-id}/applicants")
    public ResponseEntity<?> getApplicantsToRecruit(@PathVariable("recruit-id") Long recruitId) {
        List<ApplicantCardResponse> applicants = recruitFacade.selectApplicantsToRecruit(
                recruitId);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    /* 공고 좋아요 */
    @PostMapping("/{recruit-id}/like")
    public ResponseEntity<?> toggleRecruitLike(@PathVariable("recruit-id") Long recruitId) {
        return new ResponseEntity<>(recruitFacade.changeRecruitLikeStatus(recruitId),
                HttpStatus.OK);
    }

    /* 공고 검색 결과 조회 */
    @GetMapping("/search")
    public ResponseEntity<?> searchRecruit(
            @ModelAttribute RecruitSearchFilter recruitSearchFilter) {
        return new ResponseEntity<>(recruitFacade.getRecruitByFilter(recruitSearchFilter),
                HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getRecruits() {
        List<RecruitCardResponse> recruits = recruitFacade.getRecruitsAll();

        return new ResponseEntity<>(recruits, HttpStatus.OK);
    }

    /* 공고 채팅방 리스트 조회 */
    @GetMapping("/{recruit-id}/chat-rooms")
    public ResponseEntity<?> getChatRooms(@PathVariable("recruit-id") Long recruitId) {
        List<ChatRoomResponse> chatRooms = recruitFacade.selectChatRoomsOfRecruit(recruitId);

        return new ResponseEntity<>(chatRooms, HttpStatus.OK);
    }
}
