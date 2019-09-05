package net.mshome.twisted.tmall.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mshome.twisted.tmall.enumeration.ErrorCode;

/**
 * 错误码枚举类
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper<T> {

    private int code = ErrorCode.OK.getValue();

    private String message = "操作成功";

    private T result;

    public ResultWrapper(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
