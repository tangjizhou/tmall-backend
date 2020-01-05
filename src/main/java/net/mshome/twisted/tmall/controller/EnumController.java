package net.mshome.twisted.tmall.controller;

import net.mshome.twisted.tmall.enumeration.DataState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 枚举数据接口
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2020/1/5
 */
@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/data-state")
    public List<DataState> listDataState() {
        return Arrays.asList(DataState.values());
    }

}
