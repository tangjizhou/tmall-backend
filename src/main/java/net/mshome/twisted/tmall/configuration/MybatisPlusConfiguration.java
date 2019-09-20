package net.mshome.twisted.tmall.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import net.mshome.twisted.tmall.handler.mybatis.MybatisPlusMetaObjectHandler;
import net.mshome.twisted.tmall.interceptor.MybatisSqlInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/8/26
 */
@Configuration
@MapperScan("net.mshome.twisted.tmall.mapper")
public class MybatisPlusConfiguration {

    @Bean
    public MybatisSqlInterceptor mybatisSqlInterceptor() {
        return new MybatisSqlInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MybatisPlusMetaObjectHandler mybatisPlusMetaObjectHandler() {
        return new MybatisPlusMetaObjectHandler();
    }

}
