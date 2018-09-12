package org.hson.helloWorld.service;

import org.hson.helloWorld.model.User;
import org.hson.helloWorld.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {

    private UserRepository userRepository;

    public ServiceUser(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    public Iterable<User> getAll(){
     return this.userRepository.findAll();
    }


    public void save(User user){
        this.userRepository.save(user);
    }

    public User findByName(String name){
        return this.userRepository.findUserByName(name);
    }

}
