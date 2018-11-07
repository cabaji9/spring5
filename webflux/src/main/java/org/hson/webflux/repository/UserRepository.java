package org.hson.webflux.repository;

import org.hson.webflux.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Integer> {

    User findUserByName(String name);
}
