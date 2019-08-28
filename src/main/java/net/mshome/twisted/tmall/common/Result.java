package net.mshome.twisted.tmall.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-18
 * @description TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private boolean success = true;

    private String message = "success";

    private T result;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
