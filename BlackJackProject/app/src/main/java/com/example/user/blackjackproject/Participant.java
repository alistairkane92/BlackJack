package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public interface Participant {

    public void addCardToHand(Card card);

    public int getHandValue();

    public String describeHand();

    public String getName();

    public Hand getHand();

    public String getStickOrTwist();

    public void setStickOrTwist(String string);



}