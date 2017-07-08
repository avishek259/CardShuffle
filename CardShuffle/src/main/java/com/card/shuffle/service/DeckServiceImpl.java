package com.card.shuffle.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.card.shuffle.model.Card;
import com.card.shuffle.model.Deck;
import com.card.shuffle.model.Rank;
import com.card.shuffle.model.Suit;
import com.google.gson.Gson;

@Service("userService")
public class DeckServiceImpl implements DeckService {

	public static List<Deck> deckList = new ArrayList<Deck>();
	
	@Override
	public Deck findByName(String deckName) {
		for(Deck deck : deckList){
			if(null != deck.getName() && deck.getName().equalsIgnoreCase(deckName)){
				return deck;
			}
		}
		return null;
	}

	@Override
	public List<Deck> findAllDecks() {
		return deckList;
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
		return findByName(deck.getName())!=null;
	}

	@Override
	public Deck shuffleCards(Deck deck,String shufflingType) {
		switch(shufflingType){
			case "Simple": {
				Collections.shuffle(deck.getCards());
				break;
			}
		    case "Complex": {
		    	//Hand-shuffling logic  :
		    	deck.setCards(handShuffle(deck.getCards()));
		    	break;
		    }
		    default:{
		    	Collections.shuffle(deck.getCards());
		    }
		}
		
		return deck;
	}

	@Override
	public void deleteDeckByName(String deckName) {
		for (Iterator<Deck> iterator = deckList.iterator(); iterator.hasNext(); ) {
		    Deck deck = iterator.next();
		    if (null != deck.getName() && deck.getName().equalsIgnoreCase(deckName)) {
		        iterator.remove();
		    }
		}
	}
	
	/*
	  Algorithm:
	  Split deck into two halves and then interleave two halves.
	 */
	/**
	 * 
	 * @param cards
	 * @return
	 */
	private <T> List<T> handShuffle(List<T> cards){
	    List<T> halfDeck_1   = new LinkedList<T>();
	    List<T> halfDeck_2   = new LinkedList<T>();
	    List<T> shuffledDeck = new LinkedList<T>();
	    for(int i=0; i < cards.size(); i++){
	      if(i<(cards.size()/2)){
	        halfDeck_1.add(cards.get(i));
	      }else{
	        halfDeck_2.add(cards.get(i));
	      }
	    }
	    //assuming there are two equal halves
	    if(halfDeck_1.size()==halfDeck_2.size()){
	      for(int j=0; j < halfDeck_1.size(); j++){
	        shuffledDeck.add(halfDeck_1.get(j));
	        shuffledDeck.add(halfDeck_2.get(j));
	      }
	    }
	    return shuffledDeck;
	  }
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		Deck deck = new Deck();
		deck.setName("Avi");
		System.out.println("Json :"+gson.toJson(deck));
	}
	
}
