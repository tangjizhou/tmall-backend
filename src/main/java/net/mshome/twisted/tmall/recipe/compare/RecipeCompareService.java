package net.mshome.twisted.tmall.recipe.compare;

import net.mshome.twisted.tmall.recipe.model.*;
import org.apache.commons.collections4.CollectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2021/4/13
 */
public class RecipeCompareService {

    public List<CompareVO> compare(Recipe one, Recipe another) {

        return Collections.emptyList();
    }

    private List<FlatParamVO> flatRecipe(Recipe recipe) {
        Header header = recipe.getHeader();
        Body body = recipe.getBody();
        String path = header.getPpId().getName();

        Arrays.stream(body.getClass().getDeclaredFields()).forEach(field -> {
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), Object.class);


            } catch (IntrospectionException e) {
                e.printStackTrace();
            }

        });
        return Collections.emptyList();
    }

    private void flatObject(Object o, List<String> paths, List<FlatParamVO> results) throws Exception {
        XmlNode xmlNode = (XmlNode) o;
        if (CollectionUtils.isEmpty(paths)) {
            paths = new ArrayList<>();
            paths.add(xmlNode.getName());
        }
        if (o instanceof Param) {
            paths.add(xmlNode.getName());
            results.add(new FlatParamVO());
            paths.remove(paths.size() - 1);
            return;
        }
        for (Field field : o.getClass().getDeclaredFields()) {
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), XmlNode.class);
            Object sub = descriptor.getReadMethod().invoke(o);
            if (sub instanceof List) {

            }
            flatObject(sub, paths, results);
        }


    }

    private void flatObjects(List<Object> objects, List<String> paths, List<FlatParamVO> results) {
        for (Object object : objects) {
            if (object instanceof List) {
                flatObjects((List) object, paths, results);
            }
            if (object instanceof Param) {
                paths.add(((Param) object).getName());
                results.add(new FlatParamVO());

            }


        }


    }


}
