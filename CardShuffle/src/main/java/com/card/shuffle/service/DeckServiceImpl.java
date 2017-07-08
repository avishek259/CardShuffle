package com.card.shuffle.service;

import java.util.ArrayList;
import java.util.List;

import com.card.shuffle.model.Card;
import com.card.shuffle.model.Deck;
import com.card.shuffle.model.Rank;
import com.card.shuffle.model.Suit;

public class DeckServiceImpl implements DeckService {

	public static List<Deck> deckList = new ArrayList<Deck>();
	@Override
	public Deck findByName(String deckName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deck> findAllDecks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createDeck(Deck deck) {
		Deck newDeck = new Deck();
		newDeck.setName(deck.getName());
		List<Card> cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
            	cards.add(new Card(rank, suit));
            }
		}
		newDeck.setCards(cards);
		deckList.add(newDeck);
	}

	@Override
	public boolean isDeckExist(Deck deck) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shuffleCards(Deck deck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDeckByName(String deckName) {
		// TODO Auto-generated method stub
		
	}

}
