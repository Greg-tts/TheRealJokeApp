package com.tts.joke;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tts.joke.model.Joke;
import com.tts.joke.repository.JokeRepository;
import com.tts.joke.service.JokeServiceImpl;
import com.tts.joke.service.JokeServiceInt;

@RunWith(SpringRunner.class)
public class JokeServiceTest {
	
	@Configuration
	static class ServiceConfiguration{
		@Bean
		public JokeServiceInt configuration(){
			return new JokeServiceImpl();
		}
	}
	
	private static Joke tank;
	private static Joke nsfwJoke;
	private static List<Joke> jokeList;
	private static List<Joke> filteredList;
	
	@Autowired
	JokeServiceInt jokeService;
	
	@MockBean
	JokeRepository jokeRepository;

	@Before
	public void setUp(){
		tank = new Joke("Tank Joke", "Knock knock. Who’s there? Tank. Tank who?", "You’re welcome!", false);
		nsfwJoke = new Joke("NSFW Joke", "NSFW Test?", "NSFW Test", true);

		jokeList = new ArrayList<>();
		jokeList.add(tank);
		
		jokeList = new ArrayList<>( Arrays.asList(tank, nsfwJoke) );
		filteredList = new ArrayList<>( Arrays.asList(tank) );
	}
	
	@Test
	public void givenJokeId_returnJoke() {
		Mockito.when(jokeRepository.findJokeById(tank.getId())).thenReturn(tank);

		Joke found = jokeService.findJokeById(tank.getId());
		
		assertThat(tank).isEqualToComparingFieldByField(found);		
	}
	
	@Test
	public void givenJokeName_returnJoke() {
		Mockito.when(jokeRepository.findJokeByName(tank.getName())).thenReturn(tank);

		Joke found = jokeService.findJokeByName(tank.getName());
		
		assertThat(tank).isEqualToComparingFieldByField(found);		
	}
	
	@Test
	public void givenJokes_returnAllJokes() {
		Mockito.when(jokeRepository.findAll()).thenReturn(jokeList);

		List<Joke> found = jokeService.findAll();
		
		assertEquals(jokeList, found);
	}
	
	@Test
	public void givenJokeNsfw_returnFilteredJokes() {
		Mockito.when(jokeRepository.findAllJokesByNsfw(tank.getNsfw())).thenReturn(filteredList);

		List<Joke> found = jokeService.findByNsfw(tank.getNsfw());
		
		assertEquals(filteredList, found);
	}

}
