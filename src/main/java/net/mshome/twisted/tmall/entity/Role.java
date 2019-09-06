package net.mshome.twisted.tmall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 状态:1:正常,0:禁用
     */
    private Boolean state;

}
