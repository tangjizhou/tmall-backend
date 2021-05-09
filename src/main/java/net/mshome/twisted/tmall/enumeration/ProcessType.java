package net.mshome.twisted.tmall.enumeration;

/**
 * 流程类型
 *
 * @author tangjizhou
 * @since 2020/8/20
 */
public enum ProcessType {

    CHECK_IN("商品入库流程"),
    CHECK_OUT("商品出库流程"),
    ;

    private final String cnName;

    ProcessType(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }
}
