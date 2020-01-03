package net.mshome.twisted.tmall.aop.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import net.mshome.twisted.tmall.aop.handler.mybatis.MybatisPlusMetaObjectHandler;
import net.mshome.twisted.tmall.aop.interceptor.MybatisSqlInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/8/26
 */
@Configuration
@EnableTransactionManagement
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
