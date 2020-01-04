package net.mshome.twisted.tmall.aop.configuration;

import net.mshome.twisted.tmall.common.UserRealm;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * shiro配置
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/3
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        var filterChainDefinitionMap = new HashMap<String, String>();
        filterChainDefinitionMap.put("/*", "anon");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }


    @Bean
    public UserRealm userRealm(CacheManager cacheManager, PasswordMatcher passwordMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(passwordMatcher);
        userRealm.setCachingEnabled(true);
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(CacheManager cacheManager, UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public CacheManager cacheManager() {
        return new CacheManager() {
            @Override
            public <K, V> Cache<K, V> getCache(String s) throws CacheException {
                return new Cache<>() {
                    private static final String CACHE_PREFIX = "tmall:shiro:cache:";

                    private String getCacheKey(Object key) {
                        return CACHE_PREFIX + key;
                    }

                    @Override
                    @SuppressWarnings("unchecked")
                    public V get(K k) throws CacheException {
                        return (V) redisTemplate.opsForValue().get(getCacheKey(k));
                    }

                    @Override
                    public V put(K k, V v) throws CacheException {
                        redisTemplate.opsForValue().set(getCacheKey(k), v);
                        return v;
                    }

                    @Override
                    public V remove(K k) throws CacheException {
                        V v = get(k);
                        redisTemplate.delete(getCacheKey(k));
                        return v;
                    }

                    @Override
                    public void clear() throws CacheException {
                        throw new UnsupportedOperationException();
                    }

                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public Set<K> keys() {
                        return Collections.emptySet();
                    }

                    @Override
                    public Collection<V> values() {
                        return Collections.emptySet();
                    }
                };
            }
        };
    }


    @Bean
    public PasswordMatcher passwordMatcher() {
        return new PasswordMatcher();
    }

}
