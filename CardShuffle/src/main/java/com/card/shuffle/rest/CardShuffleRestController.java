package com.card.shuffle.rest;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.card.shuffle.model.Deck;
import com.card.shuffle.service.DeckService;
import com.card.shuffle.util.CustomErrorType;

@RestController
@RequestMapping("/cardshuffle")
public class CardShuffleRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(CardShuffleRestController.class);
	 @Autowired
	 DeckService deckService;
	 
	/**
	 * Retrieve single deck 
	 * @param deckName
	 * @return
	 */
	@RequestMapping(value = "/card/{deckName}", method = RequestMethod.GET)
		public ResponseEntity<?> getDecks(@PathVariable("deckName") String deckName) {
		logger.info("Fetching Deck with name {}", deckName);
        Deck deck = deckService.findByName(deckName);
        if (deck == null) {
            logger.error("Deck with name {} not found.", deckName);
            return new ResponseEntity(new CustomErrorType("Deck with name " + deckName 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Deck>(deck, HttpStatus.OK);
    }
	
	/**
	 * Retrieve a list of decks
	 * @return
	 */
	 @RequestMapping(value = "/card/", method = RequestMethod.GET)
	    public ResponseEntity<List<Deck>> listAllDecks() {
	        List<Deck> decks = deckService.findAllDecks();
	        if (decks.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // We cam also decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Deck>>(decks, HttpStatus.OK);
	    }
	 
	/**
	 * 
	 * Create a new deck
	 * @param deck
	 * @param ucBuilder
	 * @return
	 */
	 @RequestMapping(value = "/card/", method = RequestMethod.POST)
	    public ResponseEntity<?> createDeck(@RequestBody Deck deck, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating Deck : {}", deck);
	 
	        if (deckService.isDeckExist(deck)) {
	            logger.error("Unable to create. A Deck with name {} already exist", deck.getName());
	            return new ResponseEntity(new CustomErrorType("Unable to create. A Deck with name " + 
	            deck.getName() + " already exist."),HttpStatus.CONFLICT);
	        }
	        deckService.createDeck(deck);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/cardshuffle/card/{deckName}").buildAndExpand(deck.getName()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	 /**
	  * 
	  * Shuffle cards in given deck 
	  * @param deckName
	  * @param deck
	  * @return
	  */
	 @RequestMapping(value = "/card/{deckName}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateDeck(@PathVariable("deckName") String deckName, @RequestBody Deck deck) {
	        logger.info("Updating Deck with name {}", deckName);
	 
	        deck = deckService.findByName(deckName);
	 
	        if (deck == null) {
	            logger.error("Unable to update. Deck with name {} not found.", deckName);
	            return new ResponseEntity(new CustomErrorType("Unable to upate. Deck with name " + deckName + " not found."),
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        //Logic to shuffle the cards
	        deckService.shuffleCards(deck);
	        //userService.updateUser(currentUser);
	        return new ResponseEntity<Deck>(deck, HttpStatus.OK);
	    }
	 /**
	  * Delete a deck
	  * @param deckName
	  * @return
	  */
	 @RequestMapping(value = "/card/{deckName}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deleteDeck(@PathVariable("deckName") String deckName) {
	        logger.info("Fetching & Deleting Deck with name {}", deckName);
	 
	        Deck deck = deckService.findByName(deckName);
	        if (deck == null) {
	            logger.error("Unable to delete. Deck with name {} not found.", deckName);
	            return new ResponseEntity(new CustomErrorType("Unable to delete. Deck with name " + deckName + " not found."),
	                    HttpStatus.NOT_FOUND);
	        }
	        deckService.deleteDeckByName(deckName);
	        return new ResponseEntity<Deck>(HttpStatus.NO_CONTENT);
	    }
}

