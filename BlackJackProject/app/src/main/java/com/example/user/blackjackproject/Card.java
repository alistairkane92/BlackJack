package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public class Card {

        private Suit suit;
        private Rank rank;
        private Suit image;

        public Card(Suit suit, Rank rank, Suit image){
            this.suit = suit;
            this.rank = rank;
            this.image = image;
        }

        public Suit getSuitImage(){ return image;}

        public Suit getSuit() {
            return suit;
        }

        public Rank getRank() {
            return rank;
        }

        public int getValue() {
            return this.rank.getValue();
        }

        public String prettyName(){
            return (rank.getName() + " of " + suit.getSuit());
        }
}

