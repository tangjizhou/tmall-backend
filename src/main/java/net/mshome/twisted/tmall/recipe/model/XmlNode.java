package net.mshome.twisted.tmall.recipe.model;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * xml node
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
@Getter
@Setter
public class XmlNode {

    @JacksonXmlProperty(isAttribute = true)
    private Long id = IdWorker.getId();
    @JacksonXmlProperty(isAttribute = true)
    private String name = this.getClass().getSimpleName();

}
