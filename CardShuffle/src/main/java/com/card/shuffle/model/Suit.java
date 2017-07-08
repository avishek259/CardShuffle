package com.card.shuffle.model;

public enum Suit {
	CLUBS(2), DIAMONDS(3), HEARTS(4), SPADES(1);

	private int Suitpoints;

	Suit(int points) {
		this.Suitpoints = points;
	}

	public int getSuitpoints() {
		return this.Suitpoints;
	}

}
