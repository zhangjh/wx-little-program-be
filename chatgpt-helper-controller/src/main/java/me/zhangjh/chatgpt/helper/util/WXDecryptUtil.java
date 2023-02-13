package me.zhangjh.chatgpt.helper.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:37 PM 2023/2/13
 * @Description
 */
public class WXDecryptUtil {
    public static String getUserInfo(String encryptedData, String sessionKey, String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + 1;
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                return new String(resultByte, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
        String data = "CGiuIvsI0LacTsZWnpFsmrU7VxYGddpPwxOY5M+jjsrtdxpivY5NGHTy5plEy7nhL9KgosAj8TQQ7Uca8y7rrAr8OZUOr59j1wH07GrRxmdFU9j3kP2vp8PT27WOM7pWDMRsCYABFvnLSIrl+qlLw0tIvDT7paymH+7yzmqwONlF8dnhJvwvBi9ajA8BZqn6jrDRy0WAcSqMxAf+jTo4rv1dAau9MdzzsY7Ha4DeILFk3HvgzPXJ0y3EvuO9wKwWc976PBI75Nw9en82WeRGU8tT1hb61UZruXFh/6SqKP30rQPdtn+b+EXftM7sQA/9JYEuBIyIIBxMNoNR9vc6Y+iCxssnc6zHHjOeAcPEhEXkj1cOOAnJ2VOwirUiSVcUH+AjfTQce8MSyMaCHWcoq78LFDAbHGuX9frCG+z2q82U1HLFdq/qpBwdBzwfTui4at2aabShyJwjuJ+XkPGHsQ==";
        String session = "a2PKgJpfPntjqeLFeWl/oA==";
        String iv = "7see7Be/a4XDGc5YINeAHQ==";
        String userInfo = WXDecryptUtil.getUserInfo(data, session, iv);
        System.out.println(userInfo);
    }
}
