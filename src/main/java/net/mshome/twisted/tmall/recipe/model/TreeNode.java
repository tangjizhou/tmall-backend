package net.mshome.twisted.tmall.recipe.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * tree node
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
@Getter
@Setter
public class TreeNode {

    private XmlNode xmlNode;
    private String parentPath;
    private List<TreeNode> children;

    public String getName() {
        return this.xmlNode.getName();
    }

    public String getPath() {
        return this.parentPath.concat("/").concat(getName());
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
        this.children.forEach(v -> v.setParentPath(this.getPath()));
    }

}
