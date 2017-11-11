package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

public class Runner {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Deck deck = new Deck();
        Game game = new Game(deck, ui);
        game.play();
    }
}
