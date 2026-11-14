package com.cardGame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.cardGame.Card.Suit;

public class BlackJack {
	ArrayList<Card> cards = new ArrayList<>();

	boolean hasLost = false;
	int dealerAmount = 0;
	int playerAmount = 0;
	boolean hasStand = false;

	Stack <Card> playerHand;
	Stack <Card> dealerHand;
	Queue<Card> deck;
	
	//the part of your program that's in charge of game rules goes here
	public void setup() {
		deck = new LinkedList();
		playerHand = new Stack();
		dealerHand = new Stack();
		//make every card, shuffle it and stick it into deck
		for(Card.Suit s: Card.Suit.values()) {
			for(int i=1; i<=13; i++) {
				cards.add(new Card(i, s));
			}
		}
		Collections.shuffle(cards);
		
		for(Card c: cards) {
			deck.add(c);
		}
		
		Card firstPlayerCard = deck.poll();
		playerHand.add(firstPlayerCard);
		Card secondPlayerCard = deck.poll();
		playerHand.add(secondPlayerCard);
		
		Card firstDealerCard = deck.poll();
		dealerHand.add(firstDealerCard);
		Card secondDealerCard = deck.poll();
		secondDealerCard.hide();
		dealerHand.add(secondDealerCard);
		
	}
	

	
	
	public Card hit() {
		
		Card r = deck.poll();
		playerHand.add(r);
		return r;
}
	
	public void bet() {
		
		
		
		
		
	}

	public void calculateWinner() {
		int playerAmountFromTwentyOne = 21 - playerAmount;
		int dealerAmountFromTwentyOne = 21 - dealerAmount;

		if (playerAmountFromTwentyOne < dealerAmountFromTwentyOne) {
			System.out.println("You won");

		}
		else {
			System.out.println("You lost");
		}

	}


	public void calculateTie() {
		int playerAmountFromTwentyOne = 21 - playerAmount;
		int dealerAmountFromTwentyOne = 21 - dealerAmount;

		if (playerAmountFromTwentyOne == dealerAmountFromTwentyOne) {
			System.out.println("You tied");

		}

	}

	public int calculateDealerValue() {
		int dealerAmount = 0;
		for (int i = 0; i < dealerHand.size(); i++) {

			dealerAmount += dealerHand.get(i).value;

		}
		return dealerAmount;
	}
	
	public void dealerTurn() {
		hasStand = true;
		for (int i = 0; i < dealerHand.size(); i++) {
			
			dealerAmount = dealerAmount + dealerHand.get(i).value;
			
			
		}
		System.out.println(dealerAmount);
		
		while (calculateDealerValue() <= 16) {
			Card selectedCard = deck.poll();
			selectedCard.hide();
			dealerHand.add(selectedCard);
			
		}

			for (int i = 0; i <dealerHand.size(); i++) {
				Card currentCard = dealerHand.get(i);
				currentCard.show();
			}

			return;
			
		}
	
	public void calculatePlayerValue() {
		for (int i = 0; i < playerHand.size(); i++) {
			
			playerAmount = playerAmount + playerHand.get(i).value;
			
			
		}
		System.out.println(playerAmount);
		
		if (playerAmount > 21) {
			System.out.println("You lost");
			hasLost = true;
			return;
			
		}
		else  {

			hit();

		}
		
	}
	
	
	
	
}
