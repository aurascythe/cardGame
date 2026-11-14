package com.cardGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BlackJack {
	ArrayList<Card> cards = new ArrayList<>();

	boolean hasLost = false;
	int dealerAmount = 0;
	int playerAmount = 0;
	boolean hasStand = false;
	boolean hasWon = false;
	int betValue = 0;

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
		
		calculatePlayerValue();

		while (playerAmount > 21) {

    		playerHand.clear();

    		Card newFirstPlayerCard = deck.poll();
    		playerHand.add(newFirstPlayerCard);

    		Card newSecondPlayerCard = deck.poll();
    		playerHand.add(newSecondPlayerCard);

   	 		calculatePlayerValue();
	}

	}
	

	
	
	public Card hit() {
		
		Card r = deck.poll();
		playerHand.add(r);
		return r;
}
	
	public void bet() {
		
		Scanner myObj = new Scanner(System.in);
        System.out.println("Please input the amount you are willing to bet");
        String betInput = myObj.nextLine();
		betValue = Integer.parseInt(betInput);
        System.out.println("Transaction accepted");
		
		}

	public void calculateWinner() {

    if (playerAmount > 21) {
        hasWon = false;
        System.out.println("You lost");
    }
    else if (dealerAmount > 21) {
        hasWon = true;
        System.out.println("Dealer busted! You won");

		int profit = (int)(betValue * 1.5);
		System.out.println("You have won " + profit);
    }
    else if (playerAmount > dealerAmount) {
        hasWon = true;
        System.out.println("You won");

		int profit = (int)(betValue * 1.5);
		System.out.println("You have won " + profit);
    }
    else if (playerAmount < dealerAmount) {
        hasWon = false;
        System.out.println("You lost");
    }
    else {
        System.out.println("You tied");
    }
}

public int calculateDealerValue() {
    int dealerAmount = 0;
    int aceCount = 0;

    for (int i = 0; i < dealerHand.size(); i++) {
        Card currentCard = dealerHand.get(i);

        if (currentCard.value == 1) {
            dealerAmount += 11;
            aceCount++;
        }
        else {
            dealerAmount += Math.min(currentCard.value, 10);
        }
    }

    while (dealerAmount > 21 && aceCount > 0) {
        dealerAmount -= 10;
        aceCount--;
    }

    return dealerAmount;
}


public void dealerTurn() {
    hasStand = true;

    for (int i = 0; i < dealerHand.size(); i++) {
        dealerHand.get(i).show();
    }

    while (calculateDealerValue() <= 16) {
        Card selectedCard = deck.poll();

        if (selectedCard == null) {
            break;
        }

        dealerHand.add(selectedCard);
        selectedCard.show();
    }

    dealerAmount = calculateDealerValue();
    System.out.println("Dealer amount: " + dealerAmount);
}

	public void calculatePlayerValue() {
    playerAmount = 0;
    int aceCount = 0;

    for (int i = 0; i < playerHand.size(); i++) {
        Card currentCard = playerHand.get(i);

        if (currentCard.value == 1) {
            playerAmount += 11;
            aceCount++;
        }
        else {
            playerAmount += Math.min(currentCard.value, 10);
        }
    }

    while (playerAmount > 21 && aceCount > 0) {
        playerAmount -= 10;
        aceCount--;
    }

    System.out.println("Player amount: " + playerAmount);

    if (playerAmount > 21) {
        System.out.println("You lost");
        hasLost = true;
    }
}
}