package net.mshome.twisted.tmall.configuration;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 系统通用简单配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/10/16
 */
@Configuration
public class TmallConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer builderCustomizer() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeSerializeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeDeserializeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return builder -> {
            // 所有Long类型转换成String到前台
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(dateFormatter));
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeSerializeFormatter));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeDeserializeFormatter));
        };
    }

}