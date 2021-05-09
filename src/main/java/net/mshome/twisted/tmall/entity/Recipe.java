package net.mshome.twisted.tmall.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * recipe
 *
 * @author tangjizhou
 * @since 2021/4/13
 */
@Getter
@Setter
@JacksonXmlRootElement
public class Recipe extends XmlNode implements Cloneable {

    @JacksonXmlProperty
    private Param ppId;
    @JacksonXmlProperty
    private Param mdln;
    @JacksonXmlProperty
    private Param softv;

    @JacksonXmlProperty
    private Body body;

    @Override
    public Recipe clone() {
        return JSON.parseObject(JSON.toJSONString(this), Recipe.class);

    }

}
