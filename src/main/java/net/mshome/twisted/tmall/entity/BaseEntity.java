package net.mshome.twisted.tmall.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库实体类的父类，必需字段
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2364242973192805226L;

    /**
     * 数据表主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    /**
     * 数据创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime createTime;
    /**
     * 数据更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime updateTime;

}
