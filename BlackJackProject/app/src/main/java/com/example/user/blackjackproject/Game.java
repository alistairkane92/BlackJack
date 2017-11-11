package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

import java.util.ArrayList;


public class Game {

    private Deck deck;
    private Dealer dealer;
    private ArrayList<Participant> players;
    private Ui ui;

    public Game(Deck deck, Ui ui) {
        this.deck = deck;
        this.dealer = new Dealer("Dealer");
        this.players = new ArrayList<>();


        players.add(new Player("Player 1"));
        players.add(dealer);

        this.ui = ui;
    }

    public ArrayList<Participant> getPlayers() {
        return players;
    }

    public void play() {
        dealer.startDealing(this.players, this.deck);


        for (int i = 0; i < players.size()-1; i++) {
            Participant player = players.get(i);
            if (player.getHand().checkIfAce()){
                player.getHand().makeAcesLowIfBust();
            }
            while (!player.getHand().checkBust() && player.getStickOrTwist().equals("t")) {
                ui.showDealtCards(player);
                String answer = ui.askTwistOrStick(player);
                player.setStickOrTwist(answer);

                if (player.getStickOrTwist().equals("t")) {
                    dealer.dealCard(player, deck);
                }
            }
        }

//      Dealer logic

        ui.showDealtCards(dealer);

        while (dealer.checkShouldTwist(dealer.getHandValue())) {
            dealer.dealCard(dealer, deck);
            ui.showDealtCards(dealer);
        }

        Participant winner = checkWinner(players);
        ui.showWinner(winner);

    }

    public Participant checkWinner(ArrayList<Participant> players) {
        Participant player1 = players.get(0);
        Participant player2 = players.get(1);

        if (!player1.getHand().checkBust() && !player2.getHand().checkBust()) {
            if (player1.getHandValue() == player2.getHandValue()) {
                return player1;
            } else {
                return player1.getHandValue() > player2.getHandValue() ? player1 : player2;
            }

        } else {

            return player1.getHand().checkBust() ? player2 : player1;

        }
    }
}

