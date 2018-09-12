package org.hson.helloWorld.controller;

import lombok.extern.slf4j.Slf4j;
import org.hson.helloWorld.model.User;
import org.hson.helloWorld.service.ServiceUser;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private ServiceUser userService;

    public UserController(ServiceUser userService) {
        this.userService = userService;
    }

    @RequestMapping("/findAll")
    public Iterable<User> findAll(){
        return userService.getAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody  User user){
        userService.save(user);
    }


    @RequestMapping("/findByName/{name}")
    public User findByName(@PathVariable String name){
        log.info("findByName {}",name);
        return userService.findByName(name);
    }




}
