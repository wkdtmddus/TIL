package com.ssafy.ios.lineup.backend.common.validator;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_LOGIN_USER;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_RECRUIT_APPLICANT;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_RECRUIT_WRITER;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_USER;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public boolean isWriter(User loginUser, Recruit recruit) {
        User writer = recruit.getWriter();
        return loginUser.equals(writer);
    }

    public boolean isApplicant(User loginUser, ContractRequest contractRequest) {
        User applicant = contractRequest.getApplication().getApplicant();
        return loginUser.equals(applicant);
    }

    public void checkLoginUserNonNull(User loginUser) throws CustomException {
        if (loginUser == null) {
            throw new CustomException(NO_LOGIN_USER);
        }
    }

    public void checkUserNonNull(User user) throws CustomException {
        if (user == null) {
            throw new CustomException(NO_USER);
        }
    }

    public void checkWriter(User loginUser, Recruit recruit) throws CustomException {
        if (!isWriter(loginUser, recruit)) {
            throw new CustomException(NO_RECRUIT_WRITER);
        }
    }

    public void checkApplicant(User loginUser, ContractRequest contractRequest)
            throws CustomException {
        if (!isApplicant(loginUser, contractRequest)) {
            throw new CustomException(NO_RECRUIT_APPLICANT);
        }
    }
}
