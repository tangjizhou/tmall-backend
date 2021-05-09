package net.mshome.twisted.tmall;

import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * performance test
 *
 * @author tangjizhou
 * @since 2020/10/6
 */
public class LambdaTest {

    @Data
    private static class A {

        private Long id = new Random().nextLong();
        private String name1 = UUID.randomUUID().toString();
        private String name2 = UUID.randomUUID().toString();
        private String name3 = UUID.randomUUID().toString();
        private String name4 = UUID.randomUUID().toString();
        private String name5 = UUID.randomUUID().toString();
        private String name6 = UUID.randomUUID().toString();
        private String name7 = UUID.randomUUID().toString();
        private String name8 = UUID.randomUUID().toString();
        private String name9 = UUID.randomUUID().toString();
        private String name10 = UUID.randomUUID().toString();
        private String name11 = UUID.randomUUID().toString();
        private String name12 = UUID.randomUUID().toString();
        private String name13 = UUID.randomUUID().toString();
        private String name14 = UUID.randomUUID().toString();
        private String name15 = UUID.randomUUID().toString();
        private String name16 = UUID.randomUUID().toString();
        private String name17 = UUID.randomUUID().toString();
        private String name18 = UUID.randomUUID().toString();
        private String name19 = UUID.randomUUID().toString();
        private String name20 = UUID.randomUUID().toString();

    }


    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        List<A> arr = new ArrayList<>();
        for (int i = 0; i < 15_0000; i++) {
            A a = new A();
            arr.add(a);
        }
        System.out.println(System.currentTimeMillis());

        Map<Long, A> collect = arr.stream().collect(Collectors.toMap(A::getId, Function.identity()));
        System.out.println(collect.size());
        System.out.println(System.currentTimeMillis());

    }

}
