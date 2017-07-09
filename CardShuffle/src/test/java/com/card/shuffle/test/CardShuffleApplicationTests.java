package com.card.shuffle.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.card.shuffle.model.Deck;

import junit.framework.Assert;
@SpringBootConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CardShuffleApplicationTests {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/cardshuffle";
	@Test
    @SuppressWarnings({ "unchecked", "deprecation" })
    public void listAllDecks(){
		boolean testpassed = false;
        System.out.println("Testing listAllDecks API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<Deck> decks = restTemplate.getForObject(REST_SERVICE_URI+"/card/", List.class);
        //List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/card/", List.class);
         
        if(decks!=null && !decks.isEmpty()){
        	testpassed = true;
            for(Deck deck : decks){
                System.out.println("Deck name : "+deck.getName());
            }
        }else{
            System.out.println("No decks exist----------");
        }
        Assert.assertTrue(testpassed);
    }
     

}
