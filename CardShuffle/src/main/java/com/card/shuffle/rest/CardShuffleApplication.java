package com.card.shuffle.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan("com.card.shuffle.service")
public class CardShuffleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardShuffleApplication.class, args);
	}
}
