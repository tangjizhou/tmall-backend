package net.mshome.twisted.tmall.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * body
 *
 * @author tangjizhou
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
