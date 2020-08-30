package net.mshome.twisted.tmall.util;

import net.mshome.twisted.tmall.entity.BaseEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 实体类工具类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/1
 */
public class EntityUtils {

    private EntityUtils() {

    }

    /**
     * 收集entity的id
     *
     * @param entities 实体类 {@link BaseEntity}
     * @return id集合
     */
    public static List<Long> collectIds(Collection<? extends BaseEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return collect(entities, BaseEntity::getId);
    }

    /**
     * 以map索引集合元素
     *
     * @param collection 集合
     * @param keyFunc    提供的索引key方法
     * @param <T>        输入类型
     * @param <K>        key类型
     * @return 以集合中对象的字段索引的对象map
     */
    public static <T, K> Map<K, T> collectToMap(Collection<T> collection, Function<T, K> keyFunc) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collectToMap(collection, keyFunc, Function.identity());
    }

    /**
     * 索引集合，以元素中的某一个属性作为索引，另一个属性作为值
     *
     * @param collection 集合
     * @param keyFunc    索引方法
     * @param valueFunc  值方法
     * @param <T>        集合类型
     * @param <K>        索引类型
     * @param <U>        值类型
     * @return 索引的map
     */
    public static <T, K, U> Map<K, U> collectToMap(Collection<T> collection, Function<T, K> keyFunc,
                                                   Function<T, U> valueFunc) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().collect(Collectors.toMap(keyFunc, valueFunc));
    }

    /**
     * 转换List中的类型
     *
     * @param list      输入List
     * @param valueFunc 转换方法
     * @param <T>       输入类型
     * @param <K>       输出类型
     * @return 转换后的List
     */
    public static <T, K> List<K> collect(Collection<T> list, Function<T, K> valueFunc) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(valueFunc).collect(Collectors.toList());
    }


}
