package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.*;

/**
 * @description: 函数式接口
 * @author: yh
 * @date: 2022/9/2
 */
@SpringBootTest
public class FunctionApplicationTest {
    /**
     * Supplier 没有输入，只有一个输出
     *
     * @author: yh
     * @date: 2022/9/2
     */
    Supplier<String> supplier = () -> "hello world";

    /**
     * Consumer只有一个输入，没有输出
     *
     * @author: yh
     * @date: 2022/9/2
     */
    Consumer<String> consumer = i -> System.out.println("this is a " + i);


    /**
     * Function输入T 输出R
     *
     * @author: yh
     * @date: 2022/9/2
     */
    Function<Integer, Integer> function = i -> i * i;


    /**
     * UnaryOperator 输入输出都是T
     *
     * @author: yh
     * @date: 2022/9/2
     */
    UnaryOperator<Integer> unaryOperator = i -> i * i;


    /**
     * BiFunction 输入T U  输出R
     *
     * @author: yh
     * @date: 2022/9/2
     */
    BiFunction<Integer, Integer, String> biFunction = (i, j) -> i + "*" + j + "=" + i * j;


    /**
     * 输入T 输出`boolean`值
     * @author:  yh
     * @date:  2022/9/2
     */
    Predicate<String> predicate = "hello"::equals;
    @Test
    public void test2() {
    }


    @Test
    public void test() {
        String s = supplier.get();
        System.out.println(s);
        consumer.accept("apple");
        System.out.println(function.apply(9));
        System.out.println(unaryOperator.apply(9));
        System.out.println(biFunction.apply(3, 4));
        System.out.println(predicate.test("hello"));

    }

    private boolean generalFunction(String s) {
        return "hello".equals(s);
    }
}
