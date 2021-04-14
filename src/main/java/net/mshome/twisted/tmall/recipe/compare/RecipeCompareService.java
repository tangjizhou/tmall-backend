package net.mshome.twisted.tmall.recipe.compare;

import com.google.common.collect.Sets;
import net.mshome.twisted.tmall.recipe.model.*;
import net.mshome.twisted.tmall.util.EntityUtils;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
public class RecipeCompareService {

    public static void main(String[] args) throws Exception {
        Recipe recipe = new Recipe();
        Header header = new Header();
        Param param1 = new Param("param1", 2, 3, 4);
        param1.setName("param1");
        Param param2 = new Param("param2", 2, 3, 4);
        param2.setName("param2");
        Param param3 = new Param("param3", 2, 3, 4);
        param3.setName("param3");

        header.setMdln(param1);
        header.setPpId(param2);
        header.setSoftv(param3);

        Body body = new Body();
        body.setName("body");
        body.setParams(List.of(param1, param2, param3));
        ParamSet paramSet1 = new ParamSet();
        paramSet1.setName("step1");
        paramSet1.setParams(List.of(param1, param2));
        ParamSet paramSet2 = new ParamSet();
        paramSet2.setName("pointTable1");
        paramSet2.setParams(List.of(param1, param2));
        paramSet1.setParamSets(List.of(paramSet2));
        body.setParamSets(List.of(paramSet1));
        recipe.setHeader(header);
        recipe.setBody(body);


        Recipe recipe2 = recipe.clone();
        recipe2.setName("recipe2");
        new RecipeCompareService().compare(List.of(recipe, recipe2)).forEach(System.out::println);
    }

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
