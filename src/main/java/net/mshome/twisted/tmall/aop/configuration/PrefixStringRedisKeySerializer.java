package net.mshome.twisted.tmall.aop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * 自定义redis key的序列化，分环境
 *
 * @author tangjizhou
 * @since 2020/9/15
 */
@Component
public class PrefixStringRedisKeySerializer extends StringRedisSerializer {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;


    public PrefixStringRedisKeySerializer() {
        super(StandardCharsets.UTF_8);
    }

    @Override
    public String deserialize(byte[] bytes) {
        String value = super.deserialize(bytes);
        return Optional.ofNullable(value).map(v -> value.replaceFirst(prefix(v), "")).orElse(null);
    }

    @Override
    public byte[] serialize(String value) {
        return super.serialize(prefix(value));
    }

    private String prefix(String key) {
        return String.join(":", applicationName, activeProfile, key).concat(":");

    }

}
