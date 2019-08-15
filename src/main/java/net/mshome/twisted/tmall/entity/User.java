package net.mshome.twisted.tmall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.mshome.twisted.tmall.enumeration.UserStateEnum;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String username;

    private String password;

    /**
     * 0:无效,1:有效,9:锁定
     */
    private UserStateEnum state;

    private String realName;

    private String address;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
