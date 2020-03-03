package net.mshome.twisted.tmall.annotation;

/**
 * 默认的无权限返回值提供者
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/3
 */
public class DefaultPermissionControlledValueSupplier implements PermissionControlledValueSupplier {

    @Override
    public Object supply() {
        return null;
    }

}
