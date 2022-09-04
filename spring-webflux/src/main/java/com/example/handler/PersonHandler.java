package com.example.handler;

import com.example.entity.Person;
import com.example.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Mono和Flux是spring webflux用来处理响应式工作流的核心api，
 * Mono表示0或1个元素，
 * Flux表示0至N和元素，
 * @author:  yh
 * @date:  2022/9/4
 */

@Component
public class PersonHandler {

    @Resource
    private PersonService personService;

    public Mono<ServerResponse> listPeople(ServerRequest request) {
        Flux<Person> people = personService.getList();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(people, Person.class);
    }

    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return request.bodyToMono(Person.class)
                .flatMap(i -> personService.savePerson(i))
                .flatMap(p -> ServerResponse.ok().bodyValue(p));
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        int personId = Integer.parseInt(request.pathVariable("id"));
        return personService.getPerson(personId)
                .flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}