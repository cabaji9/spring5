package org.hson.helloWorld;

import lombok.extern.slf4j.Slf4j;
import org.hson.helloWorld.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hyun Woo Son on 10/11/18
 **/
@Slf4j
public class RestTemplateTest {


    @Rule
    public TestWatcher name = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            log.info("\n\n********Iniciando prueba:  {} ************\n ", description.getMethodName());
        }
    };

    private String url = "http://localhost:8090/";

    RestTemplate rest;

    @Before
    public void init() {
        rest = new RestTemplate();
    }

    @Test
    public void testRestTemplateForObject() {


        rest.postForObject(url + "/user/save", new User(1, "lala"), Void.class);


        List<User> userList = rest.getForObject(url + "/user/findAll", List.class);
        log.info("userList  {}", userList);

    }

    @Test
    public void testRestTemplateForEntity() {

        HttpEntity<User> httpEntity = new HttpEntity<>(new User(1,"lala"));

        ResponseEntity responseEntity = rest.postForEntity(url + "/user/save", httpEntity, Void.class);
        assertTrue(responseEntity.getStatusCode().value() == HttpStatus.CREATED.value());


       ResponseEntity<List>  responseEntityGet=  rest.getForEntity(url + "/user/findAll",List.class);
        assertTrue(responseEntityGet.getStatusCode().value() == HttpStatus.OK.value());


       List<User> userList = responseEntityGet.getBody();

       log.info("user list {}",userList);

       assertTrue(userList.size() == 1);


    }
}
