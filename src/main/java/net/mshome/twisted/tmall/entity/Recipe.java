package net.mshome.twisted.tmall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

/**
 * 配方
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/3/23
 */
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Recipe {

    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String path;

    @XmlElement
    private Header header;


}
