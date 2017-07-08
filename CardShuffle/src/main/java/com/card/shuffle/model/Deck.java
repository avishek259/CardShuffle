package com.card.shuffle.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	private String name;
	private List<Card> cards = new ArrayList<Card>();
	private boolean shuffled = false;
	public boolean isShuffled() {
		return shuffled;
	}
	public void setShuffled(boolean shuffled) {
		this.shuffled = shuffled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
