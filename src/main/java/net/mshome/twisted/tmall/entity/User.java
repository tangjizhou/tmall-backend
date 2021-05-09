package net.mshome.twisted.tmall.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户数据库映射类
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String realName;

    private String address;

}
