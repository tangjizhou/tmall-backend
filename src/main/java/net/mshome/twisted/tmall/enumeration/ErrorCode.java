package net.mshome.twisted.tmall.enumeration;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 系统错误码
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/4
 */
public enum ErrorCode implements IEnum<Integer> {
    /**
     * 成功
     */
    OK(0),
    /**
     * 没有登陆
     */
    NOT_LOGIN(4003),
    /**
     * 请求参数或者方式错误
     */
    BAD_REQUEST(4000),
    /**
     * 服务器错误
     */
    SERVER_INTERNAL_ERROR(5000);


    Integer value;

    ErrorCode(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
