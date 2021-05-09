package net.mshome.twisted.tmall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 测试restTemplate
 *
 * @author tangjizhou
 * @since 2020/6/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {


    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void testInterceptor() {
        Map<String, String> map = Map.of();
        String url = "http://localhost:8090/tmall-service/product/list?token=123";
        String body = restTemplate.getForEntity(url, String.class, map).getBody();
        System.out.println(body);
    }


}
