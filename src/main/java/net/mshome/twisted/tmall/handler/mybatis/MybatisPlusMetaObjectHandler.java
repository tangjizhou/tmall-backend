package net.mshome.twisted.tmall.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 公共字段填充
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/8/26
 */
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setIfAbsent("createTime", LocalDateTime.now(), metaObject);
        setIfAbsent("updateTime", LocalDateTime.now(), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setIfAbsent("updateTime", LocalDateTime.now(), metaObject);
    }

    private void setIfAbsent(String fieldName, Object value, MetaObject metaobject) {
        if (getFieldValByName(fieldName, metaobject) != null) {
            return;
        }
        setFieldValByName(fieldName, value, metaobject);
    }

}
