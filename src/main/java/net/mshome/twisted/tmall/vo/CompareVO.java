package net.mshome.twisted.tmall.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * compare result vo
 *
 * @author tangjizhou
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
