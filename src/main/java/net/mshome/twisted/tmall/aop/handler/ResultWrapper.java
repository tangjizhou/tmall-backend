package net.mshome.twisted.tmall.aop.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mshome.twisted.tmall.enumeration.ErrorCode;

/**
 * 返回给前台的消息包装类
 *
 * @author tangjizhou
 * @date 2019-08-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper<T> {

    @Builder.Default
    private int code = ErrorCode.OK.getValue();

    @Builder.Default
    private String message = "操作成功";

    private T result;

}
