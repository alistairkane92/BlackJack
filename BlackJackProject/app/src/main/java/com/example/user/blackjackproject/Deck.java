package com.example.user.blackjackproject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 10/11/2017.
 */

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        generateDeck();
        shuffle();
    }

    public int getNumberOfCards(){
        return cards.size();
    }

    public void generateDeck(){
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                cards.add(new Card(suit, rank, suit.getImage()));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card getCard(){
        return cards.remove(0);
    }
}
