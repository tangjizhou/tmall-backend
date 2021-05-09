package net.mshome.twisted.tmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author tangjizhou
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
