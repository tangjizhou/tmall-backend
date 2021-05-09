package net.mshome.twisted.tmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String code;

    private String name;

}
