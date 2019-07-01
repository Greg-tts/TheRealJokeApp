package com.tts.joke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.joke.model.Joke;
import com.tts.joke.repository.JokeRepository;

@Service
public class JokeService {
	
	@Autowired
	private JokeRepository jokeRepository;
	
	public List<Joke> findAll(){
		return jokeRepository.findAll();
	}

	public Joke findJokeById(Long id) {
		return jokeRepository.findJokeById(id);
	}
	
	public void saveJoke(Joke joke) {
		jokeRepository.save(joke);
	}
	
	public void deleteAll() {
		jokeRepository.deleteAll();
	}
	
	public void deleteJokeById(Long id) {
		jokeRepository.deleteById(id);
	}
	
	public void updateJokeById(Long id, Joke jokeData) {
		Joke jokeToReplace = jokeRepository.findJokeById(id);
		String content = jokeData.getContent();
		String punchLine = jokeData.getPunchLine();
		Boolean nsfw = jokeData.getNsfw();
		if(content != null) jokeToReplace.setContent(content);
		if(punchLine != null) jokeToReplace.setPunchLine(punchLine);
		if(nsfw != null) jokeToReplace.setNsfw(nsfw);
		jokeRepository.save(jokeToReplace);
	}
	
}
