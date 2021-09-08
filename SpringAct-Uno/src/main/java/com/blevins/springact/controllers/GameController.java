package com.blevins.springact.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.blevins.springact.models.Game;
import com.blevins.springact.services.Turn;
import com.blevins.springact.services.UnoGameAPI;
@CrossOrigin(maxAge = 3600)
@RestController
public class GameController {
	@PostMapping("/games")
	Turn postGame(@RequestBody Game game) {
		// Use the UnoGameAPI to
		// 1. Return the game name of an existing name
		// 2. Or create a new game and return its name
		UnoGameAPI gameAPI = UnoGameAPI.getGame(game.getGameId());
		return gameAPI.getTurn();
	}
	@GetMapping("/games")
	List<Game> getGames() {
		// Use the UnoGameAPI to
		// 1. Get all existing game names
		// 2. This is a new method for the UnoGameAPI
		List<String> names = UnoGameAPI.getGameNames();
		List<Game> games = new ArrayList<>();
		for (String name : names) {
			games.add(new Game(name));
		}
		return games;
	}
	@GetMapping("/games/{name}")
	Turn getGames(@PathVariable String name) {
		// Use the UnoGameAPI to
		// 1. Get all existing game names
		// 2. This is a new method for the UnoGameAPI
		List<String> names = UnoGameAPI.getGameNames();
		if (names.contains(name)) {
			UnoGameAPI game = UnoGameAPI.getGame(name);
			return game.getTurn();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}
	@PutMapping("/games")
	Turn getTurn(@RequestBody Game game) {
		// Use the UnoGameAPI to
		// 1. Get a game turn for the given game key (name)
		// 2. Do not create a game if the game key is unknown
		// 3. Instead, when game key is not known:
		// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find
		// resource");
		// System.out.println("getTurn");
		UnoGameAPI gameAPI = UnoGameAPI.getGame(game.getGameId());
		if (gameAPI != null) {
			gameAPI.nextTurn();
			return gameAPI.getTurn();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}
	@DeleteMapping("/games")
	Game deleteGame(@RequestBody Game game) {
		// Use the UnoGameAPI to
		// 1. Get a game turn for the given game key (name)
		// 2. Do not create a game if the game key is unknown
		// 3. Instead, when game key is not known:
		// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find
		// resource");
		// System.out.println("getTurn");
		UnoGameAPI gameAPI = UnoGameAPI.deleteGame(game.getGameId());
		if (gameAPI != null) {
			return new Game(game.getGameId());
		}
		System.out.println("failure");
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}
}
























