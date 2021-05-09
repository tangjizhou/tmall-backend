package net.mshome.twisted.tmall;

import net.mshome.twisted.tmall.entity.Header;
import net.mshome.twisted.tmall.entity.Recipe;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * TODO
 *
 * @author tangjizhou
 * @since 2021/3/23
 */
public class XmlTest {

    @Test
    public void test() throws JAXBException {

        Recipe recipe = new Recipe();
        recipe.setId("1");
        recipe.setName("clean");
        recipe.setPath("/recipe/clean");
        Header header = new Header();
        header.setId("2");
        header.setVersion("3");
        recipe.setHeader(header);

        JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(recipe, new File("test.xml"));


    }

}
