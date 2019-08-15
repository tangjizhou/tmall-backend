package net.mshome.twisted.tmall.enumeration;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-15
 * @description 用户状态枚举类
 */
public enum UserStateEnum implements IEnum<Integer> {
    /**
     * 无效状态
     */
    INVALID(0),

    /**
     * 有效状态
     */
    VALID(1),

    /**
     * 锁定状态
     */
    LOCKED(9);


    private Integer value;

    UserStateEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
