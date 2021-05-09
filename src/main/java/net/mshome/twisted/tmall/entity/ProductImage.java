package net.mshome.twisted.tmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductImage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;

    private String fileName;

    private String fileType;

    private String location;


}
