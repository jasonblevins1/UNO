package com.blevins.springact.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class UnoService {
	
	private HashMap<String, UnoGameAPI> map = new HashMap<>();
	public UnoService() {
		// System.out.println("UnoService here");
//		for(int i = 0; i < 200; i++) {
//			map.put("" + i, UnoGameAPI.getGame("" + i));			
//		}
	}
	
	public Turn getValue(String key) {
		if (map.containsKey(key)) {
			UnoGameAPI game = map.get(key);
			if (game.nextTurn()) {
				return game.getTurn();
			}
			else {
				return null;
			}
		}
		return null;
	}
}
