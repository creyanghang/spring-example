package com.stream;

import com.Stream.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/**
 * @author yh
 * @description
 * @date 2022/6/7
 */
@SpringBootTest
public class OptionalApplicationTest {

    /**
     * 优雅的判断非空
     * <p>
     * 两个Optional 对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse()方法仍然创建了User对象。与之相反，orElseGet()方法不创建User对象。
     *
     * @author yh
     * @date 2022/6/7
     */

    @Test
    public void test2() {
        //------------开始--------
//        String ss = null;
//        if (ss == null) {
//            throw new RuntimeException();
//        }
//        //if等于以下代码
//        Optional.ofNullable(ss).orElseThrow(() -> new RuntimeException());

        // ------------结束--------

        List<Student> list = null;
        //      System.out.println(Optional.ofNullable(list).orElse(Collections.emptyList()));

//        Student student = null;
//        System.out.println("Using orElseGet");
//        Optional.ofNullable(student).orElseGet(() -> createStudent());
//
//
//        System.out.println("Using orElse");
//        Optional.ofNullable(student).orElse(createStudent());

        //orElseGet在对象不为空的情况下，代码不会执行
        Student student2 = new Student();
        System.out.println("Using orElseGet");
        Student student = Optional.ofNullable(student2).orElseGet(() -> createStudent());
        System.out.println(student);

        // orElse在对象不为空的情况下代码一样会执行，只不过取到的值是那个非空的值
        System.out.println("Using orElse");
        Student student1 = Optional.ofNullable(student2).orElse(createStudent());
        System.out.println(student1);
    }

    private Student createStudent() {
        System.out.println("create student");
        return new Student();
    }
}
