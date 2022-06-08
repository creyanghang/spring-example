package com.stream;

import com.Stream.entity.Clazz;
import com.Stream.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yh
 * @description
 * @date 2022/6/8
 */
@SpringBootTest
public class Stream2ApplicationTest {

    @Test
    public void test2() {

        // 无限流
//        IntStream.iterate(0, k -> k + 1 > 9 ? 0 : k + 1)
//                .forEach(k ->
//                        System.out.print(" " + k)
//                );


        List<Clazz> list = new ArrayList<>();
        list.add(new Clazz(1,"1班", Arrays.asList(1,2,3)));
        list.add(new Clazz(1,"1班", Arrays.asList(1,2,3)));
        list.add(new Clazz(1,"1班", Arrays.asList(4,5,6)));

        Collection<Integer> collect = list.stream().flatMap(p -> p.getStudentId().stream()).collect(Collectors.toList());
        System.out.println(collect);

    }

    @Test
    public void test() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "张三", 18, 2));
        list.add(new Student(1, "李四", 18, 2));
        list.add(new Student(1, "王五", 18, 1));
        // 根据班级id分组
        Map<Integer, List<Student>> collect = list.stream().collect(Collectors.groupingBy(Student::getClazzId));
        System.out.println(collect);
    }
}
