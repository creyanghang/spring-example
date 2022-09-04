package com.example.dao;

import com.example.entity.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonDao {

    /**
     * 获取所有person
     * @author: yh
     * @date: 2022/9/4
     * @return Flux<Person>
     */
    Flux<Person> getList();

    /**
     * 保存对象
     * @param person person
     * @author: yh
     * @date: 2022/9/4
     * @return Mono<Void>
     */
    Mono<Void> savePerson(Person person);

    /**
     * 根据id查询
     * @param id id
     * @author: yh
     * @date: 2022/9/4
     * @return Mono<Person>
     */
    Mono<Person> getPerson(Integer id);
}
