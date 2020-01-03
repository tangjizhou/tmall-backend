package net.mshome.twisted.tmall.enumeration;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 数据状态
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/3
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DataState implements IEnum<String> {
    VALID("有效"),
    INVALID("无效");

    private String cnName;

    DataState(String cnName) {
        this.cnName = cnName;
    }

    @Override
    public String getValue() {
        return name();
    }

    public String getCnName() {
        return cnName;
    }
}
