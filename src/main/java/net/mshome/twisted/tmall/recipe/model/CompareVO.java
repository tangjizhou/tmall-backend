package net.mshome.twisted.tmall.recipe.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * compare result vo
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
@Getter
@Setter
@ToString
public class CompareVO {

    private String key;
    private String path;
    private List<String> values;

}
