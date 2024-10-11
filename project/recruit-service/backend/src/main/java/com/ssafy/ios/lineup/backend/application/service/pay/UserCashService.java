package com.ssafy.ios.lineup.backend.application.service.pay;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.entity.UserCash;

public interface UserCashService {

    /* 잔액 가져오기 */
    int getCashByUser(User user) throws CustomException;

    /* 잔액 업데이트 */
    int updateCashByUserAndUpdatedCash(User user, int updatedCash) throws CustomException;

    void save(UserCash userCash);
}
