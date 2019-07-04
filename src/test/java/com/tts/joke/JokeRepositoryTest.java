package com.tts.joke;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tts.joke.model.Joke;
import com.tts.joke.repository.JokeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JokeRepositoryTest {
	
	private static Joke cow;
	private static Joke nsfwJoke;
	private static List<Joke> jokeList;
	private static List<Joke> nsfwTestList;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private JokeRepository jokeRepository;
	
	@Before
	public void setUp() {
		cow = new Joke("Moo Joke", "Knock knock. Who’s there? Cow says. Cow says who?", "No, a cow says mooooo!", false);
		nsfwJoke = new Joke("NSFW Joke", "NSFW Test?", "NSFW Test", true);

		testEntityManager.persistAndFlush(cow);
		testEntityManager.persistAndFlush(nsfwJoke);
		
		jokeList = new ArrayList<>( Arrays.asList(cow, nsfwJoke) );
		nsfwTestList = new ArrayList<>( Arrays.asList(cow) );
	}
	
	@Test
	public void givenJokeById_returnJoke(){
		
		Joke found = jokeRepository.findJokeById(cow.getId());
		
		assertThat(cow).isEqualToComparingFieldByField(found);
	}
	
	@Test
	public void givenJokeByName_returnJoke(){
		
		Joke found = jokeRepository.findJokeByName(cow.getName());
		
		assertThat(cow).isEqualToComparingFieldByField(found);
	}
	
	@Test
	public void givenJoke_returnAllJokes(){
		
		List<Joke> found = jokeRepository.findAll();
				
		assertEquals(jokeList, found);
	}
	
	@Test
	public void givenNsfw_returnFilteredJokes(){
		
		List<Joke> found = jokeRepository.findAllJokesByNsfw(cow.getNsfw());

		assertEquals(nsfwTestList, found);
	}
}
