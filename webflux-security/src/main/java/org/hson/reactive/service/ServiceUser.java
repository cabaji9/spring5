package org.hson.reactive.service;

import org.hson.reactive.model.User;
import org.hson.reactive.repository.UserRepository;
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
     return this.userRepository.findAll().log();
    }


    public Mono<User> save(User user){
       return this.userRepository.save(user);
    }

    public Flux<User> save(Mono<User> user){
        return this.userRepository.saveAll(user);
    }

    public Flux<User> save(Flux<User> user){
        return this.userRepository.saveAll(user);
    }

    public Mono<User> findByName(String name){
        return this.userRepository.findUserByName(name);
    }

    public Mono<User> findById(int id){return this.userRepository.findById(id);}

}
