package net.mshome.twisted.tmall.recipe.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * body
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
@Getter
@Setter
public class Body extends XmlNode {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "param")
    private List<Param> params;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "param")
    private List<ParamSet> paramSets;

}
