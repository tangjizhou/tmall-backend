package net.mshome.twisted.tmall.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Collections;

/**
 * 分页查询参数
 *
 * @author tangjizhou
 * @date 2020/3/7
 */
@Data
public class PageDTO {

    private Integer current = 1;

    private Integer pageSize = 10;


    public <T> Page<T> toPage() {
        return new Page<>(current, pageSize);
    }

    public <T> Page<T> toEmptyPage() {
        var page = new Page<T>(current, pageSize);
        page.setRecords(Collections.emptyList());
        return page;
    }

}
