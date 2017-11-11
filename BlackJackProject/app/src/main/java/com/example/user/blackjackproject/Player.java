package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public class Player implements Participant {
    private Hand hand;
    private String name;
    private String stickOrTwist;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.stickOrTwist = "t";

    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setStickOrTwist(String stickOrTwist) {
        this.stickOrTwist = stickOrTwist;
    }

    public String getStickOrTwist() {
        return stickOrTwist;
    }

    @Override
    public int getHandValue() {
        return this.hand.getHandValue();
    }


    @Override
    public void addCardToHand(Card card) {
        this.hand.addCard(card);
    }

    @Override
    public String describeHand() {
        String cardsInHand = "\n" + this.name + " ";
        return cardsInHand + hand.describeHand();
    }

}