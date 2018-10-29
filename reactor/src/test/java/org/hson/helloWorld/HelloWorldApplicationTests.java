package org.hson.helloWorld;

import com.google.gson.Gson;
import org.hson.helloWorld.config.HelloWorldApplication;
import org.hson.helloWorld.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HelloWorldApplication.class})
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldApplicationTests {


	@Autowired
	private MockMvc mvc;

	@Test
	public void testSaveFind() throws Exception {

		User user = new User();
		user.setId(1);
		user.setName("lala");

		mvc.perform(post("/user/save").content(json(user)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(get("/user/findAll")).andExpect(status().isOk()).andDo(print()).
				andExpect(content().string(org.hamcrest.Matchers.containsString("lala")));
	}

	protected String json(Object o) throws IOException {
		Gson gson = new Gson();
		return gson.toJson(o);


	}

}
