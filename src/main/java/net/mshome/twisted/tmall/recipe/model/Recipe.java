package net.mshome.twisted.tmall.recipe.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * recipe
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
@Getter
@Setter
@JacksonXmlRootElement
public class Recipe extends XmlNode implements Cloneable {

    @JacksonXmlProperty
    private Header header;
    @JacksonXmlProperty
    private Body body;

    @Override
    public Recipe clone() throws CloneNotSupportedException {
        return JSON.parseObject(JSON.toJSONString(this), Recipe.class);

    }

}
