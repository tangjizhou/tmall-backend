package net.mshome.twisted.tmall.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户状态前台展示字段
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStateVO implements Serializable {

    private static final long serialVersionUID = 6113666891395184560L;
    private String label;
    private Integer value;
    private String description;

}
