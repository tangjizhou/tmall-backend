package net.mshome.twisted.tmall.dto;

import lombok.*;
import net.mshome.twisted.tmall.enumeration.DataState;

/**
 * 新增用户参数
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQueryDTO extends PageDTO {

    private String username;
    private String realName;
    private DataState dataState;

}
