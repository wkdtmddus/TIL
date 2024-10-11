package com.ssafy.ios.lineup.backend.domain.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentConfirmRequest {

    private String customerKey;
    
    private String code;
}
