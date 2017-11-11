package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public class Player implements Participant {
    private Hand hand;
    private String name;
    private String stickOrTwist;
    private Integer funds;

    public Player(String name, Integer funds) {
        this.name = name;
        this.hand = new Hand();
        this.stickOrTwist = "t";
        this.funds = funds;
    }

    public void pickABetAmount(Integer bet){
        funds -= bet;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public void setName(String name) {
        this.name = name;
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

    public void returnCards(){
        this.hand.emptyHand();
    }

    public void resetHandValue(){
        this.hand.emptyValues();
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