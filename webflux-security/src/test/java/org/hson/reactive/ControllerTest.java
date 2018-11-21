package org.hson.reactive;

import org.hson.reactive.config.ReactiveApplication;
import org.hson.reactive.model.User;
import org.hson.reactive.rest.UserController;
import org.hson.reactive.service.ServiceUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Hyun Woo Son on 11/9/18
 **/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ReactiveApplication.class})
@SpringBootTest
public class ControllerTest {



    @Autowired
    private ServiceUser serviceUser;

    private  WebTestClient webTestClient;

    @Before
    public void before(){
        webTestClient= WebTestClient.bindToController(new UserController(serviceUser)).build();
        Flux<User> userFlux = Flux.just(new User(1,"1"),new User(2,"2"));
        serviceUser.save(userFlux).subscribe();
    }

    @Test
    public void testFindAll(){

        webTestClient.get().uri("/user/findAll").exchange().expectStatus().isOk().expectBody().json("[{'id':1,'name':'1'},{'id':2,'name':'2'}]");

    }

    @Test
    public void testSave(){
        webTestClient.post().uri("/user/save").body(Mono.just(new User(3,"3")),User.class).exchange().
                expectStatus().isOk().expectBody().json("{'id':3,'name':'3'}");

        webTestClient.get().uri("/user/findAll").exchange().expectStatus().isOk().expectBody().json("[{'id':1,'name':'1'},{'id':2,'name':'2'}," +
                "{'id':3,'name':'3'}]");



    }
}
