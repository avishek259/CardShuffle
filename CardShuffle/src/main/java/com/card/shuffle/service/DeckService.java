package com.card.shuffle.service;

import java.util.List;

import com.card.shuffle.model.Deck;

public interface DeckService {

	Deck findByName(String deckName);

	List<Deck> findAllDecks();

	void createDeck(Deck deck);

	boolean isDeckExist(Deck deck);

	void shuffleCards(Deck deck);

	void deleteDeckByName(String deckName);

}
