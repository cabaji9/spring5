package org.hson.app.service;

import org.hson.app.model.User;
import org.hson.app.repository.UserRepository;
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
        return this.userRepository.findUserByFirstName(name);
    }

}
