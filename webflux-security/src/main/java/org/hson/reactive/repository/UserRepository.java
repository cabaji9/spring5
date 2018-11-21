package org.hson.reactive.repository;

import org.hson.reactive.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Integer> {

    Mono<User> findUserByName(String name);
}
