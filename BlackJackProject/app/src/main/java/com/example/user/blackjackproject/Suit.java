package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public enum Suit {


    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades");


    private String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
