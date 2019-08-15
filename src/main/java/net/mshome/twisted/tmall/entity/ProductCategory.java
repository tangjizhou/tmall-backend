package net.mshome.twisted.tmall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String categoryName;

    /**
     * 0:停用,1:有效
     */
    private Integer state;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
