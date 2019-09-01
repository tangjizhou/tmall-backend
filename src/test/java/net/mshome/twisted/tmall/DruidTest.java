package net.mshome.twisted.tmall;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/1
 * @description druid密码加密
 */
public class DruidTest {

    @Test
    public void generateEncryptPasswordTest() throws Exception {
        String password = "tmallpass";
        ConfigTools.main(new String[]{password});
    }

}
