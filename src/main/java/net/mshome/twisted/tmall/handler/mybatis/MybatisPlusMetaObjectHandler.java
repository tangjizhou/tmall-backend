package net.mshome.twisted.tmall.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019/8/26
 * @description 公共字段填充
 */
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object value = getFieldValByName("createTime", metaObject);
        if (value != null) {
            return;
        }
        setFieldValByName("crateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object value = getFieldValByName("updateTime", metaObject);
        if (value != null) {
            return;
        }
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);

    }

}
