package com.example.handler;

import com.example.entity.Person;
import com.example.dao.IPersonDao;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * Mono和Flux是spring webflux用来处理响应式工作流的核心api，
 * Mono表示0或1个元素，
 * Flux表示0至N和元素，
 *
 * 这里等同于MVC架构中的Service层
 *
 * @author: yh
 * @date: 2022/9/4
 */
@Component
public class PersonHandler {

    @Resource
    private IPersonDao personDao;

    /**
     * 查询集合
     * @param request request
     * @author: yh
     * @date: 2022/9/4
     * @return Mono<ServerResponse>
     */
    public Mono<ServerResponse> listPeople(ServerRequest request) {
        Flux<Person> people = personDao.getList();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(people, Person.class);
    }

    /**
     * 保存对象
     * @param request request
     * @author: yh
     * @date: 2022/9/4
     * @return Mono<ServerResponse>
     */
    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return request.bodyToMono(Person.class)
                .flatMap(i -> personDao.savePerson(i))
                .flatMap(p -> ServerResponse.ok().bodyValue(p));
    }
    /**
     * 根据id查询
     * @param request
     * @author: yh
     * @date: 2022/9/4
     * @return Mono<ServerResponse>
     */
    public Mono<ServerResponse> getPerson(ServerRequest request) {
        int personId = Integer.parseInt(request.pathVariable("id"));
        return personDao.getPerson(personId)
                .flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}