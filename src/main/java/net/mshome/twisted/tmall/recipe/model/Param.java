package net.mshome.twisted.tmall.recipe.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * param
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
@Getter
@Setter
public class Param extends XmlNode {

    @JacksonXmlProperty(isAttribute = true)
    private Object value;
    @JacksonXmlProperty(isAttribute = true)
    private Object min;
    @JacksonXmlProperty(isAttribute = true)
    private Object max;
    @JacksonXmlProperty(isAttribute = true)
    private Object limit;

}
