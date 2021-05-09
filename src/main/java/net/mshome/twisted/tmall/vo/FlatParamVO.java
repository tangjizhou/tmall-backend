package net.mshome.twisted.tmall.vo;

import lombok.*;

/**
 * flatted param vo
 *
 * @author tangjizhou
 * @since 2021/4/13
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlatParamVO {

    private String name;
    private String path;
    private String value;
    private String spec;
    private int pathDepth;
}
