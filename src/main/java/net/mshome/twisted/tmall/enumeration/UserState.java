package net.mshome.twisted.tmall.enumeration;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import net.mshome.twisted.tmall.vo.UserStateVO;

/**
 * 用户状态枚举类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 */
public enum UserState implements IEnum<Integer> {
    /**
     * 无效状态
     */
    INVALID(0, "无效"),

    /**
     * 有效状态
     */
    VALID(1, "有效"),

    /**
     * 锁定状态
     */
    LOCKED(9, "锁定");


    private Integer value;
    private String description;

    UserState(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * 查询时，利用jackson转换所有属性
     */
    @JsonValue
    public UserStateVO toUserStateVO() {
        return UserStateVO.builder().label(this.name())
                .value(this.value).description(this.description)
                .build();
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
