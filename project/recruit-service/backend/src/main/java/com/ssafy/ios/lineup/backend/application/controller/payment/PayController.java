package com.ssafy.ios.lineup.backend.application.controller.payment;

import com.ssafy.ios.lineup.backend.application.facade.pay.PayFacade;
import com.ssafy.ios.lineup.backend.domain.dto.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PayController {

    private final RestTemplate restTemplate;
    private final PayFacade payFacade;

    @Value("toss.secret-key")
    private String tossSecretKey;

    /* 토스를 통해 보증금 결제 */
    @PostMapping
    public ResponseEntity<?> payDeposit(@RequestBody PaymentRequest paymentRequest) {
        // 토스 access token 발급
        String url = "https://api.tosspayments.com/v1/brandpay/authorizations/access-token"; // https://api.tosspayments.com/v2/payments/confirm

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(tossSecretKey, "");
        headers.set("Content-Type", "application/json");

        String jsonBody = String.format(
                "{\"grantType\":\"AuthorizationCode\",\"customerKey\":\"%s\",\"code\":\"%s\"}",
                paymentRequest.getCustomerKey(),
                paymentRequest.getCode());
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String accessTokenResponse = response.getBody();
                log.info("Access Token Response: {}", accessTokenResponse);
                return ResponseEntity.ok(accessTokenResponse);
            } else {
                return ResponseEntity.status(response.getStatusCode())
                        .body(response.getBody());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("결제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}
