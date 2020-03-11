package net.mshome.twisted.tmall.annotation;

/**
 * 产品名称的默认值提供者，无权限时显示此值
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/3/3
 */
public class ProductNameDefaultValueSupplier implements DefaultValueSupplier {

    @Override
    public Object supply() {
        return "默认的产品名称";
    }

}
