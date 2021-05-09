package net.mshome.twisted.tmall.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 前台展示的用户信息
 *
 * @author tangjizhou
 * @date 2020/1/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthVO implements Serializable {

    private static final long serialVersionUID = 8454640483367336284L;
    private String username;
    private String realName;
    private Set<String> roles;
    private Set<String> permissions;

}
