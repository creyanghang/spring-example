package com.stream;

import com.Stream.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建Stream 一个数据源（如：集合、数组），获取一个流
 * 中间操作 一个中间操作链，对数据源的数据进行处理
 * 终止操作（终端操作）一个终止操作，执行中间操作链，并产生结果，到此整个流消亡。
 *
 * @author yh
 * @description stream流操作
 * @date 2022/6/7
 */
@SpringBootTest
public class StreamApplicationTest {

    @Test
    public void test4() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "张三", 18, 10));
        list.add(new Student(2, "李四", 17, 11));
        list.add(new Student(3, "李明", 14, 12));
        List<Student> list2 = list.stream().filter(p -> p.getAge() > 14).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
    }

    @Test
    public void test3() {
        List<Student> list = new ArrayList<>();
        list.add(new Student());
        list.add(new Student());
        list.stream().peek(p -> {
            p.setName("张三");
            System.out.println(p);
        });
        System.out.println(list);
        // 只有调用终端操作后才会执行peek
//        list2.collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        //检查是否没有匹配所有元素
        boolean a = list.stream().noneMatch(f -> f.equals("3"));
        System.out.println(a);
        //检查是否匹配所有元素
//        boolean b = list.stream().allMatch(f -> f.equals("1"));
//        System.out.println(b);
//
//        //检查是否至少匹配一个元素
//        boolean b1 = list.stream().anyMatch(f -> f.equals("1"));
//        System.out.println(b1);
    }

    @Test
    public void test1() {
        String[] strArr = {"hello", "java", "stream"};

        Arrays.stream(strArr, 0, 3).forEach(System.out::println);
//        Arrays.stream(strArr).filter(s -> s.length() > 4).forEach(System.out::println);
//
//        // stream返回新的集合，不会在原有集合上改变
//        Arrays.stream(strArr).filter(s -> s.length() > 4).collect(Collectors.toList());
//        System.out.println(strArr.length);
//
//        Stream<String> collect = Stream.empty();
//        // 创建空的List，避免返回null
//        List<String> objects = Collections.emptyList();
    }


}
