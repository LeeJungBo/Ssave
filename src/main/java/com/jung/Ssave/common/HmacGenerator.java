package com.jung.Ssave.common;


import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HmacGenerator {

	private static final String ALGORITHM = "HmacSHA256";
    private static final Charset STANDARD_CHARSET = Charset.forName("UTF-8");

    /**
     * Generate HMAC signature
     * @param method
     * @param uri http request uri
     * @param secretKey secret key that Coupang partner granted for calling open api
     * @param accessKey access key that Coupang partner granted for calling open api
     * @return HMAC signature
     */
    public static String generate(String method, String uri, String secretKey, String accessKey) {
        String[] parts = uri.split("\\?");
        if (parts.length > 2) {
            throw new RuntimeException("incorrect uri format");
        } else {
            String path = parts[0];
            String query = "";
            if (parts.length == 2) {
                query = parts[1];
            }
            // GMT 시간대의 현재 시간을 'yyMMdd'T'HHmmss'Z' 형식으로 설정
            SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyMMdd'T'HHmmss'Z'");
            dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
            String datetime = dateFormatGmt.format(new Date());  // 타임스탬프 생성
            String message = datetime + method + path + query; // 메시지 구성

            String signature;
            try {
            	 // 비밀 키와 알고리즘을 사용하여 서명 생성
                SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(STANDARD_CHARSET), ALGORITHM);
                Mac mac = Mac.getInstance(ALGORITHM);
                mac.init(signingKey);
                byte[] rawHmac = mac.doFinal(message.getBytes(STANDARD_CHARSET));
                signature = Hex.encodeHexString(rawHmac); // 결과를 16진수 문자열로 인코딩
            } catch (GeneralSecurityException e) {
                throw new IllegalArgumentException("Unexpected error while creating hash: " + e.getMessage(), e);
            }

            return String.format("CEA algorithm=%s, access-key=%s, signed-date=%s, signature=%s", "HmacSHA256", accessKey, datetime, signature);
        }
    }
	
}