package com.example.user.blackjackproject;

import java.util.ArrayList;

/**
 * Created by user on 10/11/2017.
 */

public class Dealer extends Player implements Participant {


    public Dealer(String name, Integer funds) {
        super(name, funds);
    }

    public void setMaxDealerFunds(Integer playerFunds) {
        setFunds(playerFunds);
    }

    public void dealCard(Participant player, Deck deck) {
        Card dealtCard = deck.getCard();
        player.addCardToHand(dealtCard);
    }


    public void startDealing(ArrayList<Participant> players, Deck deck) {
        for (Participant player : players) {
            for (int i = 0; i < 2; i++) {
                dealCard(player, deck);
            }
        }
    }

    public boolean checkShouldTwist(int handValue) {
        return handValue < 16;
    }

}