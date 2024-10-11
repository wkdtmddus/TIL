package com.ssafy.ios.lineup.backend.application.facade.pay;

import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.CASH_PAY;
import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.CASH_REFUND;
import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.MONEY_IN;
import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.MONEY_OUT;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserCashErrorCode.LACK_OF_CASH;

import com.ssafy.ios.lineup.backend.application.service.pay.TransferDetailService;
import com.ssafy.ios.lineup.backend.application.service.pay.UserCashService;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.entity.TransferDetail;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayFacadeImpl implements PayFacade {

    private final UserCashService userCashService;
    private final TransferDetailService transferDetailService;
    private final UserService userService;
    private final UserValidator userValidator;

    /* 입출금 및 송금, 환불 이력 가져오기 */
    @Override
    public List<TransferDetail> getTransferDetails() throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        return transferDetailService.selectTransferDetailsByUser(loginUser);
    }

    /* 충전 */
    @Override
//    @SyncCash
    public void chargeCashByAmount(int amount) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);
        // TODO : 토스페이먼츠를 통해 충전 된 것 확인
        transferDetailService.createTransferDetail(loginUser, amount, MONEY_IN);
        syncCash();
    }

    /* 계좌로 송금 */
    @Override
//    @SyncCash
    @Transactional
    public void sendCashToUserAccount(int amount) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        // TODO : Validator 로 리팩터링하기
        if (amount > getCash()) {
            throw new CustomException(LACK_OF_CASH);
        }
        transferDetailService.createTransferDetail(loginUser, amount, MONEY_OUT);
        // TODO : 토스페이먼츠를 통해 실제 계좌로 보내는 로직 추가
        syncCash();
    }

    /* 보증금 환불 */
    @Override
//    @SyncCash
    public void refundCashForDeposit(int amount) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        transferDetailService.createTransferDetail(loginUser, amount, CASH_REFUND);
        syncCash();
    }

    /* 보증금 결제 */
    @Override
//    @SyncCash
    public void payCashForDeposit(int amount) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        if (amount > getCash()) {
            throw new CustomException(LACK_OF_CASH);
        }
        transferDetailService.createTransferDetail(loginUser, amount, CASH_PAY);
        syncCash();
    }

    @Override
    public int getCash() throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);
        return userCashService.getCashByUser(loginUser);
    }

    @Override
    public void syncCash() throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);
        int updatedCash = transferDetailService.calculateUserCash(loginUser);
        userCashService.updateCashByUserAndUpdatedCash(loginUser, updatedCash);
    }
}
