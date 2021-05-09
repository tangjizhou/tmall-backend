package net.mshome.twisted.tmall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
public class SqlLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 执行的sql语句
     */
    private String executedSql;

}
