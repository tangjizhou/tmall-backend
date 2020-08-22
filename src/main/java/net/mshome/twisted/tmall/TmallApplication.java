package net.mshome.twisted.tmall;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tangjizhouchn@foxmail.com
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallApplication.class, args);

    }

}
