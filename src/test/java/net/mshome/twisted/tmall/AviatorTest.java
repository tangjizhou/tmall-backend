package net.mshome.twisted.tmall;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * 公式测试
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/9/15
 */
public class AviatorTest {

    public static void main(String[] args) {

        Map<String, Object> param = new HashMap<>();
        param.put("operand", "2");
        param.put("operator", "<=");
        param.put("value", "3");

        Object result = AviatorEvaluator.execute("operand " + param.get("operator") + " value", param);
        System.out.println(result);

    }

}
