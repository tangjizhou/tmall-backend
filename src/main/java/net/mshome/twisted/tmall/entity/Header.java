package net.mshome.twisted.tmall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 头
 *
 * @author tangjizhou
 * @since 2021/3/23
 */
@Getter
@Setter
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Header {

    @XmlAttribute
    private String id;
    @XmlAttribute
    private String version;

}
