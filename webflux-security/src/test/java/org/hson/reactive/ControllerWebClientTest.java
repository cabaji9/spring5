package org.hson.reactive;

import lombok.extern.slf4j.Slf4j;
import org.hson.reactive.config.ReactiveApplication;
import org.hson.reactive.model.User;
import org.hson.reactive.rest.UserController;
import org.hson.reactive.service.ServiceUser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.apache.commons.compress.utils.Charsets.UTF_8;

/**
 * Created by Hyun Woo Son on 11/9/18
 **/
@Ignore
@Slf4j
public class ControllerWebClientTest {


    private WebClient webClient;

    private String userName="1";
    private String pass="pass";


    @Before
    public void before(){
       webClient = WebClient.create("http://localhost:8080");
    }

    @Test
    public void testFindAll() throws Exception{


     Flux<User> userFlux =   webClient.get().uri("/user/findAll")
             .header("Authorization", "Basic " + getBasicString(userName,pass))
             .retrieve().bodyToFlux(User.class);
     userFlux.subscribe(user -> log.info("user is {}",user));

        Thread.sleep(2000);
    }

    @Test
    public void testSave() throws InterruptedException{
       Mono<User> userMono = webClient.post().uri("/user/save").
               header("Authorization", "Basic " + getBasicString(userName,pass)).
                body(Mono.just(new User(3,"3")),User.class).
                retrieve().bodyToMono(User.class);

       userMono.log().subscribe(user -> log.info("user is {}",user));
        Thread.sleep(2000);



    }

    @Test
    public void testFindById() throws InterruptedException{

      Mono<User> userMono =  webClient.get().uri("/byId/{id}",3).
              header("Authorization", "Basic " + getBasicString(userName,pass)).
              retrieve().onStatus(HttpStatus::is4xxClientError,
                clientResponse -> Mono.just(new Exception("No se encontro"))).bodyToMono(User.class);
        userMono.subscribe(user -> log.debug("user is {}",user));
        Thread.sleep(2000);


    }

    @Test
    public void testExchangeFindById() throws InterruptedException{

       Mono<User> userMono = webClient.get().uri("/byId/{id}",3)
               .header("Authorization", "Basic " + getBasicString(userName,pass))
               .exchange().flatMap(clientResponse -> {
           log.debug("header uno {}",clientResponse.headers().header("uno").get(0));
           return clientResponse.bodyToMono(User.class);
        });
        userMono.subscribe(user -> log.debug("user is {}",user));
        Thread.sleep(3000);

    }


    private String getBasicString(String userName,String token){
       return Base64Utils
                .encodeToString((userName + ":" + token).getBytes(UTF_8));
    }
}
