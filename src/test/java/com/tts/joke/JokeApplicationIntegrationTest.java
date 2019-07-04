package com.tts.joke;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tts.joke.model.Joke;
import com.tts.joke.repository.JokeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = WebEnvironment.MOCK,
	classes = JokeApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application-integrationtest.properties" )
public class JokeApplicationIntegrationTest {
	
	private Joke boo;
	
	@Autowired
    private JokeRepository repository;
	
	@Autowired
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		boo = new Joke("Boo", "Knock knock. Who’s there? Boo. Boo who?", "No need to cry, it’s only a joke.", true);
		repository.save(boo);

	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void getIndex_returnsHtmlAnd200Status() throws Exception {
		mvc.perform(get("/")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk());
	}
	
	@Test
	public void givenJokes_getAllJokes_returnsJsonAllJokes() throws Exception {
		
		mvc.perform(get("/api/jokes")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].name", is("Boo")));
	}
	@Test
	public void givenJoke_getJoke_returnsJoke() throws Exception {
		Long jokeId = boo.getId();
		
		mvc.perform(get("/api/joke/" + jokeId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(jokeId));

	}
}
