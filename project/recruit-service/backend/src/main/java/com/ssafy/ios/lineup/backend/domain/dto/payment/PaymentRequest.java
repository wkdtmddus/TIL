package com.ssafy.ios.lineup.backend.domain.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private String customerKey;

    private String code;
}
