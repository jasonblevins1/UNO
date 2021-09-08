package com.blevins.springact.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnoGameAPI {
	private static HashMap<String, UnoGameAPI> games = new HashMap<>();
	
	Turn turn = new Turn(false, false, 0, false, 0);

	public Turn getTurn() {
		System.out.println("getTurn: " + turn);
		return turn;
	}
	
	private UnoGameAPI() {
		// Prepping the deck
		turn.setDeck(new Deck());
		turn.getDeck().populate();
		// System.out.println(turn.getDeck());
		turn.getDeck().shuffle();
		// System.out.println(turn.getDeck());

		// Establishing four players
		turn.setHands(new ArrayList<Hand>());
		turn.getHands().add(new Hand());
		turn.getHands().add(new Hand());
		turn.getHands().add(new Hand());
		turn.getHands().add(new Hand());

		// adding cards into the hands of the players
		for (int i = 0; i < 7; i++) {
			for (Hand hand : turn.getHands()) {
				hand.drawCard(turn.getDeck().dealCard());
			}
		}

		// add a card to the discard to start it, draw until there is a normal card
		turn.getDeck().discardPile();

		// System.out.println(turn.getHands());

		turn.setWinner(false);
		turn.setI(0);
		turn.setTurns(0);
		turn.setTopDiscard(turn.getDeck().topDiscard());
	}

	public static UnoGameAPI getGame(String gameName) {
		UnoGameAPI game = games.get(gameName);
		if (game == null) {
			game = new UnoGameAPI();
			games.put(gameName, game);
			
		}
		return game;
	}
	
	public static UnoGameAPI deleteGame(String gameName) {
		UnoGameAPI game = games.get(gameName);
		if (game != null) {
			games.remove(gameName);			
		}
		return game;
	}
	
	public boolean nextTurn() {


		if (turn.isWinner() == false) { // loops through players until there is a winner
			turn.setColorWasCalled(false);
			turn.setTurns(turn.getTurns() + 1);
			turn.getDeck().replenish(); // checks if deck needs to be replenished
			turn.setTopDiscard(turn.getDeck().topDiscard()); // getting the top card on the discard deck
			turn.setCurrentPlayer(turn.getI());
//			// System.out.printf("Turn: %d\n", turn.getTurns());
//			// System.out.printf("Top Card: %s\n", turn.getTopDiscard());
//			// System.out.printf("Player: %d %s \n", turn.getCurrentPlayer(), turn.getHands().get(turn.getCurrentPlayer())); 			
			Cards card = turn.getHands().get(turn.getCurrentPlayer()).hasMatch(turn.getTopDiscard()); // checking if player has a match
			turn.setCardPlayed(card);
			turn.setSpecialPlayed(false);
			if (card != null) { // if the player can play a card
				// System.out.println("Card Played:" + card); // what card they placed down
				if (card.isSpecial(card) == true) { // checks if card is special
					turn.setSpecialPlayed(true);
					switch (card.getValue()) {
					case SKIP:
						turn.setI(getNextPlayer(turn.getI()));
						break;
					case REVERSE:
						turn.setReverse(!turn.isReverse());
						break;
					case DRAWTWO:
						int nextPlayer = getNextPlayer(turn.getI());
						for (int x = 0; x < 2; x++) {
							turn.getHands().get(nextPlayer).drawCard(turn.getDeck().dealCard());
						}
						break;
					case WILD:
						turn.getHands().get(turn.getCurrentPlayer()).colorCount(card);
						turn.setColorWasCalled(true);
						turn.setColorCalled(card.getColor());
						// System.out.println("Player has called the color: " + card.getColor());
						break;
					case WILD_DRAWFOUR:
						turn.getHands().get(turn.getCurrentPlayer()).colorCount(card);
						turn.setColorWasCalled(true);
						turn.setColorCalled(card.getColor());
						// System.out.println("Player has called the color: " + card.getColor());
						int wildNextPlayer = getNextPlayer(turn.getI());
						for (int x = 0; x < 4; x++) {
							turn.getHands().get(wildNextPlayer).drawCard(turn.getDeck().dealCard());
						}
						break;
					default:
						// System.out.println("Hmm something went wrong with a special card");
						break;

					}
					// System.out.println("A special card has been played");
				}

				turn.getDeck().addToDiscard(card); // takes the card the player played and puts it at the top of the discard deck
				if (turn.getHands().get(turn.getCurrentPlayer()).isUno() == true) { // checks if player can call Uno
					turn.setUno(true);
					// System.out.printf("\nPlayer %d calls UNO!\n", turn.getCurrentPlayer());
				}
				if (turn.getHands().get(turn.getCurrentPlayer()).isWinner() == true) { // checks if player won
					turn.setWinner(true);
					// System.out.printf("\nPlayer %d Won\n", turn.getCurrentPlayer());
				}
			} else { // if the player can't match a card they pick one up
				turn.getHands().get(turn.getCurrentPlayer()).drawCard(turn.getDeck().dealCard()); // player draws one card
				// System.out.printf("Draw Card: %d \n", turn.getI()); //display player number
				// System.out.println(turn.getHands().get(turn.getCurrentPlayer())); //displayers current players hand
			}
			if (!turn.isWinner()) {
				turn.setI(getNextPlayer(turn.getI())); // getting the next player
				// System.out.println();
			}
		}
		
		return !turn.isWinner();
	}

	private int getNextPlayer(int i) {

		if (turn.isReverse() == false) {
			i++;
		} else if (turn.isReverse() == true) {
			i--;
		}
		if (i > 3) { // making sure that i does not break out of array
			i = 0;
		}
		if (i < 0) { // making sure that i does not break out of array
			i = 3;
		}
		return i;
	}

	public static List<String> getGameNames() {
		List<String> keys = new ArrayList<>(games.keySet());
		
		return keys;
		
	}

}
