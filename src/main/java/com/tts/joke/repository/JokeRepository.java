package com.tts.joke.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.joke.model.Joke;

@Repository
public interface JokeRepository extends CrudRepository<Joke, Long> {
	public Joke findJokeByName(String name);
	public Joke findJokeById(Long id);
	public List<Joke> findAll();
	public List<Joke> findAllJokesByNsfw(Boolean nsfw);
}
