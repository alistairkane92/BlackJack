package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public enum Suit {


    HEARTS("Hearts", R.drawable.heart),
    DIAMONDS("Diamonds", R.drawable.diamond),
    CLUBS("Clubs", R.drawable.club),
    SPADES("Spades", R.drawable.spade);


    private String suit;
    private int image;

    Suit(String suit, Integer image) {
        this.suit = suit;
        this.image = image;
    }

    public String getSuit() {
        return suit;
    }

    public int getImage() {
        return image;
    }
}
