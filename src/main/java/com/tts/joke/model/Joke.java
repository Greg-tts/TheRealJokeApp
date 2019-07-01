package com.tts.joke.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Joke {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Boolean nsfw;
	private String content;
	private String punchLine;
	
	public Joke() {};
	
	public Joke(Boolean nsfw, String content, String punchLine) {
		this.nsfw = nsfw;
		this.content = content;
		this.punchLine = punchLine;
	}

	public Boolean getNsfw() {
		return nsfw;
	}
	public void setNsfw(Boolean nsfw) {
		this.nsfw = nsfw;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPunchLine() {
		return punchLine;
	}
	public void setPunchLine(String punchLine) {
		this.punchLine = punchLine;
	}
	public Long getId() {
		return id;
	}
	
}
