package br.com.docesdalu.application.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConverteBase64Multipartfile {

    public static MultipartFile base64ToMultipartFile(String base64) {

        String[] baseStrs = base64.split(",");

        byte[] b = Base64.decodeBase64(baseStrs[0]);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }

        return new Base64DecodeMultipartFile(b, baseStrs[0]);
    }
}
