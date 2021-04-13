package net.mshome.twisted.tmall.recipe.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
@Getter
@Setter
public class Header {

    @JacksonXmlProperty
    private Param ppId;
    @JacksonXmlProperty
    private Param mdln;
    @JacksonXmlProperty
    private Param softv;

}
