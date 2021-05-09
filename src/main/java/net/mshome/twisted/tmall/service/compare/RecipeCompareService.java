package net.mshome.twisted.tmall.service.compare;

import com.google.common.collect.Sets;
import net.mshome.twisted.tmall.entity.Param;
import net.mshome.twisted.tmall.entity.Recipe;
import net.mshome.twisted.tmall.entity.XmlNode;
import net.mshome.twisted.tmall.util.EntityUtils;
import net.mshome.twisted.tmall.vo.CompareVO;
import net.mshome.twisted.tmall.vo.FlatParamVO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 对比服务
 *
 * @author tangjizhou
 * @since 2021/4/13
 */
@Service
public class RecipeCompareService {

    public List<CompareVO> compare(List<Recipe> recipes) {
        List<List<FlatParamVO>> recipesParams = recipes.parallelStream().map(this::flatRecipe).collect(Collectors.toList());
        Set<Map<String, FlatParamVO>> recipePaths = recipesParams.parallelStream()
                .map(recipeParams -> EntityUtils.collectToMap(recipeParams, FlatParamVO::getPath))
                .collect(Collectors.toSet());
        Set<String> paths = recipePaths.parallelStream().map(Map::keySet).reduce(new HashSet<>(), Sets::union);
        return paths.stream().map(path -> {
            CompareVO compareVO = new CompareVO();
            compareVO.setPath(path);
            compareVO.setKey(path);
            compareVO.setValues(recipePaths.stream().map(v -> v.getOrDefault(path, new FlatParamVO())
                    .getValue()).collect(Collectors.toList()));
            return compareVO;
        }).collect(Collectors.toList());
    }

    private List<FlatParamVO> flatRecipe(Recipe recipe) {
        ArrayList<FlatParamVO> results = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();
        flatObject(recipe, paths, results);
        return results;
    }

    private void flatObject(Object o, List<String> paths, List<FlatParamVO> results) {
        Assert.isTrue(o instanceof XmlNode, "Not a XmlNode instance");
        XmlNode xmlNode = (XmlNode) o;
        paths.add(xmlNode.getName());
        if (o instanceof Param) {
            Param param = (Param) o;
            results.add(new FlatParamVO(xmlNode.getName(), String.join("/", paths), String.valueOf(param.getValue()),
                    param.getSpec(), paths.size()));
        } else {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
                for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                    Object sub = descriptor.getReadMethod().invoke(o);
                    if (!(sub instanceof XmlNode) && !(sub instanceof List<?>)) continue;
                    if (sub instanceof List) flatObjects((List) sub, paths, results);
                    else flatObject(sub, paths, results);
                }
            } catch (Exception e) {
                throw new IllegalStateException("can't reflect object fields", e);
            }
        }
        paths.remove(paths.size() - 1);
    }

    private void flatObjects(List<XmlNode> xmlNodes, List<String> paths, List<FlatParamVO> results) {
        for (Object object : xmlNodes) {
            if (object instanceof List) {
                flatObjects((List) object, paths, results);
            } else {
                flatObject(object, paths, results);
            }
        }
    }


}
