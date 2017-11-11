package com.example.user.blackjackproject;

import java.util.ArrayList;

/**
 * Created by user on 10/11/2017.
 */

public class Hand {

    private ArrayList<Card> cards;
    private int value;


    public Hand() {
        this.cards = new ArrayList<>();
        value = 0;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getHandValue() {
        return this.value;
    }

    public int getNumberOfCards() {
        return this.cards.size();
    }

    public void addCard(Card card) {
        this.cards.add(card);
        this.value += card.getValue();
    }

    public String describeHand() {
        String handText = "";
        for (Card card : getCards()) {
            handText += "\n" + card.prettyName();
        }
        return handText;
    }

    public boolean checkIfAce() {
        for (Card card : cards) {
            return card.getRank() == Rank.ACE;
        }
        return false;
    }

    //method that returns number of aces in hand
    //pass method into makeAceLowIfBust() and multiply 10 * numberOfAces counter

    public int numberOfAcesInHand(){
        int numberOfAces = 0;

        for (Card card : cards) {
            if (card.getRank() == Rank.ACE){
                numberOfAces ++;
            }
        }

        return numberOfAces;
    }

    public boolean checkBust(){
        return getHandValue() > 21;
    }

    public void makeAcesLowIfBust(){
        int aceCounter = numberOfAcesInHand();

        while(checkBust() && aceCounter > 0) {
            this.value -= 10;
            aceCounter--;
        }
    }
}

