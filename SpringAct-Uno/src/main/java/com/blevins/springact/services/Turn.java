package com.blevins.springact.services;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Turn {
	private boolean reverse;
	@JsonIgnore
	private Deck deck;
	private ArrayList<Hand> hands;
	private boolean winner;
	private int turns;
	private Cards topDiscard;
	private Cards cardPlayed;
	private int currentPlayer;
	private boolean colorWasCalled;
	private CardsColor colorCalled;
	private boolean isUno;
	private int i;
	private boolean specialPlayed;
	
	public boolean isSpecialPlayed() {
		return specialPlayed;
	}

	public void setSpecialPlayed(boolean specialPlayed) {
		this.specialPlayed = specialPlayed;
	}

	public Cards getCardPlayed() {
		return cardPlayed;
	}

	public void setCardPlayed(Cards cardPlayed) {
		this.cardPlayed = cardPlayed;
	}


	public Turn(boolean reverse, boolean winner, int turns, boolean isUno, int i) {
		this.reverse = reverse;
		this.winner = winner;
		this.turns = turns;
		this.isUno = isUno;
		this.i = i;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public ArrayList<Hand> getHands() {
		return hands;
	}

	public void setHands(ArrayList<Hand> hands) {
		this.hands = hands;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public Cards getTopDiscard() {
		return topDiscard;
	}

	public void setTopDiscard(Cards topDiscard) {
		this.topDiscard = topDiscard;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isColorWasCalled() {
		return colorWasCalled;
	}

	public void setColorWasCalled(boolean colorWasCalled) {
		this.colorWasCalled = colorWasCalled;
	}

	public CardsColor getColorCalled() {
		return colorCalled;
	}

	public void setColorCalled(CardsColor colorCalled) {
		this.colorCalled = colorCalled;
	}

	public boolean isUno() {
		return isUno;
	}

	public void setUno(boolean isUno) {
		this.isUno = isUno;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public String toString() {
		return "Turn [hands=" + hands + "]";
	}
	
	
}