package org.hson.reactive.rest;

import lombok.extern.slf4j.Slf4j;
import org.hson.reactive.model.User;
import org.hson.reactive.service.ServiceUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private ServiceUser userService;

    public UserController(ServiceUser userService) {
        this.userService = userService;
    }

    @RequestMapping("/findAll")
    public Flux<User> findAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("/save")
    public Mono<User> save(@RequestBody User user){
        return userService.save(user);
    }


    @RequestMapping("/findByName/{name}")
    public Mono<User> findByName(@PathVariable String name){
        log.info("findByName {}",name);
        return userService.findByName(name);
    }




}
