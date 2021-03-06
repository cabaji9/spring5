package org.hson.helloWorld.controller;

import org.hson.helloWorld.config.HelloWorldApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewController.class)
@ContextConfiguration(classes = {HelloWorldApplication.class})
public class ViewControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Test
    public void testHomePage() throws Exception{

        mockmvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home")
        ).andDo(print()).andExpect(content().string(containsString("HELLO!")));
    }

}