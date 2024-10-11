package com.ssafy.ios.lineup.backend.application.facade.pay;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.TransferDetail;
import java.util.List;

public interface PayFacade {

    List<TransferDetail> getTransferDetails() throws CustomException;

    /* 로그인 유저의 페이 머니 충전 */
    void chargeCashByAmount(int amount) throws CustomException;

    /* 페이 머니를 로그인 유저 계좌로 송금 */
    void sendCashToUserAccount(int amount) throws CustomException;

    /* 페이머니에 보증금 입금/환불 */
    void refundCashForDeposit(int amount) throws CustomException;

    /* 페이머니에서 보증금 출금/결제 */
    void payCashForDeposit(int amount) throws CustomException;

    /* 로그인 유저의 현재 잔여 페이머니 보기 */
    int getCash() throws CustomException;

    void syncCash() throws CustomException;
}
