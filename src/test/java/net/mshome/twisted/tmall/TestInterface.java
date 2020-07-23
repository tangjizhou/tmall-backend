package net.mshome.twisted.tmall;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/7/17
 */
public class TestInterface {

    interface Base {

        String A = "1";

    }

    interface Child extends Base {

        // String A = "3";

    }


    static class BaseClass {

        public static String A = "1";

        static {
            A = "2";
        }

    }

    static class ChildClass extends BaseClass {

        public static final String A = BaseClass.A;

    }


    public static void main(String[] args) {
        List<Integer> code = Arrays.asList(1, 2, 3, 4);

        System.out.println(Base.A);
        System.out.println(Child.A);
        System.out.println(BaseClass.A);
        System.out.println(ChildClass.A);
    }

}
