package com.ssafy.ios.lineup.backend.application.service.pay;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.entity.UserCash;
import com.ssafy.ios.lineup.backend.domain.repository.pay.UserCashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCashServiceImpl implements UserCashService {

    private final UserCashRepository userCashRepository;

    @Override
    public int getCashByUser(User user) throws CustomException {
        return selectCashByUser(user).getCash();
    }

    @Override
    public int updateCashByUserAndUpdatedCash(User user, int updatedCash) throws CustomException {
        UserCash userCash = selectCashByUser(user);
        userCash.updateCash(updatedCash);
        return userCashRepository.save(userCash).getCash();
    }

    @Override
    public void save(UserCash userCash) {
        userCashRepository.save(userCash);
    }

    private UserCash selectCashByUser(User user) throws CustomException {
        return userCashRepository.findByUser(user)
                .orElse(createUserCash(user));
    }

    private UserCash createUserCash(User user) {
        return userCashRepository.save(
                UserCash.builder()
                        .user(user)
                        .build());
    }
}
