package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public enum Suit {


    HEARTS("Hearts", 1),
    DIAMONDS("Diamonds", 2),
    CLUBS("Clubs", 3),
    SPADES("Spades", 4);


    private String suit;
    private Integer image;

    Suit(String suit, Integer image) {
        this.suit = suit;
        this.image = image;
    }

    public String getSuit() {
        return suit;
    }

    public Integer getImage() {
        return image;
    }
}
