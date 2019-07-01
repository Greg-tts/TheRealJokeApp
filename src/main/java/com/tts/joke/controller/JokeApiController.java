package com.tts.joke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tts.joke.model.Joke;
import com.tts.joke.service.JokeService;

@RestController
@RequestMapping("/api")
public class JokeApiController {	
	@Autowired
	private JokeService jokeService;
	
	@GetMapping("/joke/{id}")
	public Joke findById(@PathVariable("id") Long id) {
		return jokeService.findJokeById(id);
	}
	
	@GetMapping("/jokes")
	public List<Joke> getAllJokes(){
		return jokeService.findAll();
	}
	
	@PostMapping("/joke/new")
	public String createJoke(Joke joke){
		jokeService.saveJoke(joke);
		return "Message Created Successfully";
	}
	
	@DeleteMapping("/joke/{id}")
	public String deleteJokeById(@PathVariable("id") Long id) {
		jokeService.deleteJokeById(id);
		return "Joke deleted Successfully";
	}
	
	@DeleteMapping("/jokes")
	public String deleteAllJokes() {
		jokeService.deleteAll();
		return "All jokes deleted Successfully";
	}
	
	@PostMapping("/joke/{id}")
	public String updateJokeById(@PathVariable("id") Long id, Joke joke) {
		jokeService.updateJokeById(id, joke);
		return "Jokes updated Successfully";
	}
	
}
