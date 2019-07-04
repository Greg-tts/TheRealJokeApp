package com.tts.joke.service;

import java.util.List;

import com.tts.joke.model.Joke;

public interface JokeServiceInt {
	public List<Joke> findAll();
	public List<Joke> findByNsfw(Boolean nsfw);
	public Joke findJokeById(Long id);
	public Joke findJokeByName(String name);
	public void saveJoke(Joke joke);
	public void deleteAll();
	public void deleteJokeById(Long id);
	public void updateJokeById(Long id, Joke jokeData);
}
