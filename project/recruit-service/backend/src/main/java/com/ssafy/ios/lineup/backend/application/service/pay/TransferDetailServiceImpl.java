package com.ssafy.ios.lineup.backend.application.service.pay;

import com.ssafy.ios.lineup.backend.common.constant.PayStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.TransferDetail;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.pay.TransferDetailRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferDetailServiceImpl implements TransferDetailService {

    private final TransferDetailRepository transferDetailRepository;

    @Override
    public List<TransferDetail> selectTransferDetailsByUser(User user) throws CustomException {
        return transferDetailRepository.findByUser(user);
    }

    @Override
    public TransferDetail createTransferDetail(User user, int amount, PayStatus payStatus)
            throws CustomException {
        TransferDetail transferDetail = TransferDetail.builder()
                .user(user)
                .amount(amount)
                .payStatus(payStatus)
                .build();
        return transferDetailRepository.save(transferDetail);
    }

    @Override
    public int calculateUserCash(User user) throws CustomException {
        return selectTransferDetailsByUser(user)
                .stream()
                .mapToInt(this::getAmountByStatus)
                .sum();
    }

    private int getAmountByStatus(TransferDetail transferDetail) {
        return switch (transferDetail.getPayStatus()) {
            case MONEY_IN, CASH_REFUND -> transferDetail.getAmount();
            case MONEY_OUT, CASH_PAY -> -transferDetail.getAmount();
        };
    }
}
