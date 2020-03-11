package net.mshome.twisted.tmall.annotation;

/**
 * 默认的无权限返回值提供者
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/3
 */
public class NullValueSupplier implements DefaultValueSupplier {

    @Override
    public Object supply() {
        return null;
    }

}
