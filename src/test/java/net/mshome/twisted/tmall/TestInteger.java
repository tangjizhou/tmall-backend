package net.mshome.twisted.tmall;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/7/23
 */
public class TestInteger {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);// true
        System.out.println(e == f); // false
        System.out.println(c == (a + b));// true
        System.out.println(c.equals(a + b));// true
        System.out.println(g == (a + b));// true
        System.out.println(g.equals(a + b));// false


        if (true) {
            System.out.println(1);
        } else {
            System.out.println(2);
        }

        // output class file: 包装类型equals不会做类型转换，在算术运算符的作用下会自动拆箱， -128 ～ 127 存在常量缓存

        // Integer a = 1;
        // Integer b = 2;
        // Integer c = 3;
        // Integer d = 3;
        // Integer e = 321;
        // Integer f = 321;
        // Long g = 3L;
        // System.out.println(c == d);
        // System.out.println(e == f);
        // System.out.println(c == a + b);
        // System.out.println(c.equals(a + b));
        // System.out.println(g == (long)(a + b));
        // System.out.println(g.equals(a + b));
        // System.out.println(1);

    }

}
