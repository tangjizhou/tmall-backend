package net.mshome.twisted.tmall.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductImage implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Integer productId;

    private String fileName;

    private String fileType;

    private String location;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
