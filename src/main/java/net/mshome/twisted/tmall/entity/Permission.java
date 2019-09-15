package net.mshome.twisted.tmall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Permission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限代码
     */
    private String permissionCode;

}
