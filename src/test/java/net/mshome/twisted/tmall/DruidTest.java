package net.mshome.twisted.tmall;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * druid密码加密
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019/9/1
 */
public class DruidTest {

    @Test
    public void generateEncryptPasswordTest() throws Exception {
        String password = "tmallpass";
        switch (password) {
            case "1", "3", "2" -> System.out.println(1231231);
            case "4" -> System.out.println(1231231);
            case "5", "6" -> System.out.println(1231231);
            default -> System.out.println("default");
        }

        String result = switch (password) {
            case "1" -> "1";
            case "2" -> "1";
            case "3" -> "1";
            case "4" -> "1";
            case "5", "6" -> "12";
            default -> {
                System.out.println(password);
                yield "default";
            }
        };


        ConfigTools.main(new String[]{password});
    }

}
