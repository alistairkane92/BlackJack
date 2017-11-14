package com.example.user.blackjackproject;

import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by user on 10/11/2017.
 */

public class Hand {

    private ArrayList<Card> cards;
    private int aceCounter;


    public Hand() {
        this.cards = new ArrayList<>();
        aceCounter = numberOfAcesInHand();
    }


    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getHandValue() {
        int total = 0;
        int aceCounter = numberOfAcesInHand();

        for (Card card : this.cards){
            total += card.getValue();
        }

        while (total > 21 && aceCounter > 0){
            total -= 10;
            aceCounter --;
        }

        return total;

    }

    public int getNumberOfCards() {
        return this.cards.size();
    }

    public void addCard(Card card) {
        this.cards.add(card);
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
        for (Card card : cards) {
            if (card.getRank() == Rank.ACE){
                aceCounter ++;
            }
        }

        return aceCounter;

    }

    public boolean checkBust(){
        return getHandValue() > 21;
    }


    public void emptyHand(){
        cards.clear();
    }
}

