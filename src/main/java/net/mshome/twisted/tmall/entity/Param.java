package net.mshome.twisted.tmall.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * param
 *
 * @author tangjizhou
 * @since 2021/4/13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Param extends XmlNode {

    @JacksonXmlProperty(isAttribute = true)
    private Object value;
    @JacksonXmlProperty(isAttribute = true)
    private Object min;
    @JacksonXmlProperty(isAttribute = true)
    private Object max;
    @JacksonXmlProperty(isAttribute = true)
    private Object limit;

    public String getSpec() {
        return "this is a spec";
    }

}
