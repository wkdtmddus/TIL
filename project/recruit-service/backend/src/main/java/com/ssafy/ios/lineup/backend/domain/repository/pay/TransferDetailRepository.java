package com.ssafy.ios.lineup.backend.domain.repository.pay;

import com.ssafy.ios.lineup.backend.domain.entity.TransferDetail;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferDetailRepository extends JpaRepository<TransferDetail, Long> {

    /* 유저의 송금 내역 확인 */
    List<TransferDetail> findByUser(User user);
    
}
