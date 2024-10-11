package com.ssafy.ios.lineup.backend.application.service.pay;

import com.ssafy.ios.lineup.backend.common.constant.PayStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.TransferDetail;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.List;

public interface TransferDetailService {

    /* 유저의 모든 송금 이력 가져오기 */
    List<TransferDetail> selectTransferDetailsByUser(User user) throws CustomException;

    /* 입출금 및 송금, 환불 이력 생성 */
    TransferDetail createTransferDetail(User user, int amount, PayStatus payStatus)
            throws CustomException;

    /* 유저의 입출금 및 송금, 환불 이력을 통해 유저의 현재 잔액 계산하기 (NOT "동기화까지") */
    int calculateUserCash(User user) throws CustomException;
}
