package com.blevins.springact.models;

import org.springframework.stereotype.Component;

@Component
public class Game {
	
	public Game() {
		super();
	}
	private String gameId;
	

	
	public String getGameId() {
		return gameId;	
	}

	
	public Game(String gameId) {
		super();
		this.gameId = gameId;
	}
	  

}
