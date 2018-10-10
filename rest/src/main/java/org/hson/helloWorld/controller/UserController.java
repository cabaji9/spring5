package org.hson.helloWorld.controller;

import lombok.extern.slf4j.Slf4j;
import org.hson.helloWorld.model.User;
import org.hson.helloWorld.service.ServiceUser;
import org.hson.helloWorld.util.ErrorVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user", produces = {"application/json", "application/xml"})
@CrossOrigin(origins = "*")
public class UserController {

    private ServiceUser userService;

    public UserController(ServiceUser userService) {
        this.userService = userService;
    }

    @RequestMapping("/findAll")
    public Iterable<User> findAll() {
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
        }
        catch(Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity<>(new ErrorVo(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);

    }


    @RequestMapping("/findByName/{name}")
    public ResponseEntity<User> findByName(@PathVariable String name) {
        ResponseEntity<User> responseEntity;
        log.info("findByName {}", name);
        User user = userService.findByName(name);
        if (user != null) {
            responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


}
