package net.mshome.twisted.tmall.aop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 * @description swagger2 配置
 */
@EnableSwagger2
@Configuration
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("天猫接口").description("天猫项目接口")
                .license("The Apache License, Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("http://my.csdn.net/elvishehai").build();

        return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).select()
                .apis(RequestHandlerSelectors.basePackage("net.mshome.twisted.tmall.controller"))
                .build().apiInfo(apiInfo);
    }


}
