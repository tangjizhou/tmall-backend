package net.mshome.twisted.tmall.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStateVO {

    private String label;
    private Integer value;
    private String description;

}
