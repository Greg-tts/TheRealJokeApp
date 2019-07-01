package com.tts.joke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.joke.model.Joke;
import com.tts.joke.service.JokeService;

@Controller
public class JokeController {
	
	@Autowired
	private JokeService jokeService;
	
	@GetMapping("/")
	public String findById() {
		return "index";
	}
	
	@GetMapping("/joke/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		Joke joke = jokeService.findJokeById(id);
		model.addAttribute("joke", joke);
		return "joke";
	}
	
	@GetMapping("/jokes")
	public String getAllJokes(Model model, Joke joke){
		List<Joke> jokes = jokeService.findAll();
		model.addAttribute("allJokes", jokes);
		return "jokes";
	}
	
	@GetMapping("/joke/new")
	public String getJokeForm(Joke joke){
		return "newJoke";
	}
	
	@PostMapping("/joke/new")
	public String createJoke(Joke joke, Model model){
		jokeService.saveJoke(joke);
		model.addAttribute("message", "Joke created Successfully");
		return "result";
	}
	
	@DeleteMapping("/joke/{id}")
	public String deleteJokeById(@PathVariable("id") Long id, Model model) {
		jokeService.deleteJokeById(id);
		model.addAttribute("message", "Joke deleted Successfully");
		return "result";
	}
	
	@DeleteMapping("/jokes")
	public String deleteAllJokes(Model model) {
		jokeService.deleteAll();
		model.addAttribute("message", "All jokes deleted Successfully");
		return "result";
	}
	
	@GetMapping("/joke/{id}/update")
	public String updateForm(@PathVariable("id") Long id, Joke joke, Model model) {
		Joke currentJoke = jokeService.findJokeById(id);
		model.addAttribute("currentJoke", currentJoke);
		return "updateJoke";
	}
	
	@PostMapping("/joke/{id}")
	public String updateJokeById(@PathVariable("id") Long id, Joke joke, Model model) {
		jokeService.updateJokeById(id, joke);
		model.addAttribute("message", "Jokes updated Successfully");
		return "result";
	}
	
}
