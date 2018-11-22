package org.hson.webflux.jpa.service;

import org.hson.webflux.jpa.model.User;
import org.hson.webflux.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceUser {

    private UserRepository userRepository;

    public ServiceUser(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    public Flux<User> getAll(){
     return Flux.fromIterable(this.userRepository.findAll());
    }


    public Mono<User> save(User user){
       return Mono.just(this.userRepository.save(user));
    }

    public Mono<User> findByName(String name){
        return Mono.just(this.userRepository.findUserByName(name));
    }

    public Mono<User> findById(int id){return Mono.just(this.userRepository.findById(id).orElse(null));}

}
