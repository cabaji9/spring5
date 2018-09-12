package org.hson.helloWorld;

import org.hson.helloWorld.config.HelloWorldApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HelloWorldApplication.class})
@SpringBootTest
public class HelloWorldApplicationTests {

	@Test
	public void contextLoads() {
	}

}
