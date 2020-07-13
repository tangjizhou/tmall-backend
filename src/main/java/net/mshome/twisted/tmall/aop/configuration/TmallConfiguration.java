package net.mshome.twisted.tmall.aop.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UriTemplateHandler;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 系统通用简单配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/10/16
 */
@Configuration
public class TmallConfiguration {

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
                .withSerializerModifier(new TmallBeanSerializeModifier()));
    }


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


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setHashKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setHashValueSerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    public RestTemplate restTemplate() {
        final String token = UUID.randomUUID().toString();
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.put("token", List.of(UUID.randomUUID().toString()));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new UriTemplateHandler() {
            @Override
            public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
                URI uri = new UriTemplate(uriTemplate).expand(uriVariables);
                UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri);
                builder.replaceQueryParam("token", token);
                return URI.create(builder.toUriString());
            }

            @Override
            public URI expand(String uriTemplate, Object... uriVariables) {
                URI uri = new UriTemplate(uriTemplate).expand(uriVariables);
                UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri);
                builder.replaceQueryParam("token", token);
                return URI.create(builder.toUriString());
            }
        });


        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            URI uri = request.getURI();
            UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri);
            multiValueMap.put("search", List.of(UUID.randomUUID().toString()));
            builder.replaceQueryParams(multiValueMap);
            System.out.println(builder.toUriString());
            System.out.println(new String(body, StandardCharsets.UTF_8));
            return execution.execute(request, body);
        };

        restTemplate.setInterceptors(List.of(interceptor));
        return restTemplate;
    }


}
