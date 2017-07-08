package com.card.shuffle.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	private String name;
	private List<Card> cards = new ArrayList<Card>();
	private boolean shuffeled = false;
	public boolean isShuffeled() {
		return shuffeled;
	}
	public void setShuffeled(boolean shuffeled) {
		this.shuffeled = shuffeled;
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
