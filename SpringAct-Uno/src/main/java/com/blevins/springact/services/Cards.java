package com.blevins.springact.services;


public class Cards {
	public Cards(CardsValue value, CardsColor color) {
		super();
		this.value = value;
		this.color = color;
	}

	public CardsValue getValue() {
		return value;
	}

	public CardsColor getColor() {
		return color;
	}

	public boolean isMatch(Cards card) {
		if (value == value.WILD || value == value.WILD_DRAWFOUR) { // matches if card is a wild
			return true;
		}
		if (color == card.color && card.value == card.value.WILD) { // to match to a wild card the color must match
			return true;
		}
		if (color == card.color || value == card.value) { // if the value or the color matches its a match
			return true;
		}

		return false;
	}

	public boolean isSpecial(Cards card) {
		if (card.value == value.SKIP || card.value == value.REVERSE || card.value == value.DRAWTWO
				|| card.value == value.WILD || card.value == value.WILD_DRAWFOUR) {
			return true;
		}
		return false;
	}

	public Cards isWild(Cards card, String mostCommon) {
		if (mostCommon == "Red") {
			card.color = color.RED;
		}
		if (mostCommon == "Blue") {
			card.color = color.BLUE;
		}
		if (mostCommon == "Green") {
			card.color = color.GREEN;
		}
		if (mostCommon == "Yellow") {
			card.color = color.YELLOW;
		}

		return card;
	}

	private CardsValue value;
	private CardsColor color;

	@Override
	public String toString() {
		return "[" + value + ":" + color + "]";
	}

}
