package org.hson.helloWorld;

import com.google.gson.Gson;
import org.hson.app.config.AppConfigApplication;
import org.hson.app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfigApplication.class})
@SpringBootTest
public class AppConfigApplicationTests {




	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	@WithMockUser("usuario")
	public void testSaveFind() throws Exception {

		User user = new User();
		user.setFirstName("lala");
		user.setUsername("test_user");


		mvc.perform(post("/user/save").content(json(user)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(get("/user/findAll")).andExpect(status().isOk()).andDo(print()).
				andExpect(content().string(org.hamcrest.Matchers.containsString("lala")));
	}

	protected String json(Object o) throws IOException {
		Gson gson = new Gson();
		return gson.toJson(o);


	}

}
