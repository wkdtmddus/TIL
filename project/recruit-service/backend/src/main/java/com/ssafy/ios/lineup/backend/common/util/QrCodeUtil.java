package com.ssafy.ios.lineup.backend.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.ssafy.ios.lineup.backend.common.util
 * fileName       : QrCodeUtil
 * author         : moongi
 * date           : 10/9/24
 * description    :
 */
@Slf4j
@Component
public class QrCodeUtil {
    private static final int QR_CODE_WIDTH = 300;
    private static final int QR_CODE_HEIGHT = 300;
    private static final String QR_CODE_FORMAT = "png";

    /* QR 코드를 생성하고 Base64 문자열로 반환 */
    public String generateQRCodeWithInfo(String nickname, Long recruitId, Long verificationId, String contractUuid) {
        try {
            // 1. 포함할 정보를 JSON 형태로 구성
            Map<String, Object> qrData = new HashMap<>();
            qrData.put("nickname", nickname);
            qrData.put("contractUuid", contractUuid);
            qrData.put("recruitId", recruitId);
            qrData.put("verificationId", verificationId);
            qrData.put("timestamp", System.currentTimeMillis());  // 현재 시간 정보 (Optional)

            // 2. JSON 문자열로 변환
            Gson gson = new Gson();
            String jsonString = gson.toJson(qrData);

            // 3. QR 코드 생성
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(jsonString, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT, hints);

            // 4. QR 코드를 이미지로 변환
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // 5. Base64로 인코딩
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, QR_CODE_FORMAT, baos);
            byte[] imageBytes = baos.toByteArray();

            // 6. Base64 인코딩된 문자열 반환
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (WriterException | java.io.IOException e) {
            throw new RuntimeException("QR 코드 생성 실패", e);
        }
    }

}
