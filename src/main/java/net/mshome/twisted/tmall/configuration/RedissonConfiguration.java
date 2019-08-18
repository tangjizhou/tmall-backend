//package net.mshome.twisted.tmall.configuration;
//
//import org.redisson.api.RedissonClient;
//import org.redisson.client.RedisConnection;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
///**
// * @author tangjizhouchn@foxmail.com
// * @date 2019-08-17
// * @description TODO
// */
//@Configuration
//public class RedissonConfiguration {
//
//
//
//
//
//    @Primary // 同一接口的多个实现，如果不指定名字的话，Spring 会优先选择设置 primary 为 true 的 bean
//    @Bean
//    @ConditionalOnProperty(prefix = "spring.redis.host")
//    RedissonClient redissonClient () {
//        return null;
//    }
//
//}
