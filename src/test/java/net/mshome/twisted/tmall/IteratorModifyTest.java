package net.mshome.twisted.tmall;

import com.google.common.collect.TreeMultiset;
import lombok.Data;

import java.util.Comparator;
import java.util.Random;

/**
 * 迭代时修改 测试
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/11/18
 */
public class IteratorModifyTest {

    @Data
    private static class Relation {

        Integer score = new Random().nextInt(1000);
        String name;

    }

    public static void main(String[] args) {

        TreeMultiset<Relation> queue = TreeMultiset.create(Comparator.comparing(Relation::getScore));
        queue.add(new Relation());
        queue.add(new Relation());


    }


}

