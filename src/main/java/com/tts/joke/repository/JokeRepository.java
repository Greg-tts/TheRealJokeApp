package com.tts.joke.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.joke.model.Joke;

@Repository
public interface JokeRepository extends CrudRepository<Joke, Long> {
	@Override
	public List<Joke> findAll();
	public List<Joke> findAllByNsfw(Boolean nsfw);
	public Joke findJokeById(Long id);
}
