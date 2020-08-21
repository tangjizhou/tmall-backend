package net.mshome.twisted.tmall;

import lombok.Data;
import org.apache.tomcat.util.buf.HexUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/8/22
 */
public class EntityMD5Test {

    @Data
    static class MessageRecord {

        private String a = "a";
        private String b = "b";
        private String c;
        private String d;
        private String e;

        public String md5() throws NoSuchAlgorithmException {
            System.out.println(this.toString());
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(this.toString().getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            System.out.println(Arrays.toString(digest));
            return HexUtils.toHexString(digest);
        }

    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(new MessageRecord().md5());
        System.out.println(new MessageRecord().md5());
    }


}
