package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

import java.util.ArrayList;


public class Game {

    private Deck deck;
    private Dealer dealer;
    private ArrayList<Participant> players;
    private Player player;

    public Game(Deck deck) {
        this.deck = deck;
        this.dealer = new Dealer("Dealer");
        this.players = new ArrayList<>();

        player = new Player("Player 1");
        players.add(player);
        players.add(dealer);

    }

    public ArrayList<Participant> getPlayers() {
        return players;
    }

    public void deal(){
        dealer.returnCards();
        dealer.resetHandValue();
        player.resetHandValue();
        player.returnCards();

        dealer.startDealing(this.players, this.deck);
    }

    public String showUserHand(){
        return this.players.get(0).getHand().describeHand();
    }

    public Integer showUserHandValue(){
        return this.players.get(0).getHandValue();
    }

    public String showDealerHand(){
        return this.players.get(1).getHand().describeHand();
    }

    public Integer showDealerHandValue(){
        return this.players.get(1).getHand().getHandValue();
    }

    public void stick(){
        //employs dealer logic and continues
        dealerMove();
    }

    public void twist(){
        //deals player another card and display new result
        dealer.dealCard(players.get(0), deck);
    }

    public String displayBust(){
        if (players.get(0).getHand().checkBust()){
            return "You're BUST!";
        } else {
            return " ";
        }
    }

    public void stickOrTwistAction() {
        for (int i = 0; i < players.size() - 1; i++) {
            Participant player = players.get(i);
            if (player.getHand().checkIfAce()) {
                player.getHand().makeAcesLowIfBust();
            }
            while (!player.getHand().checkBust() && player.getStickOrTwist().equals("t")) {
                player.setStickOrTwist("t");

                if (player.getStickOrTwist().equals("t")) {
                    dealer.dealCard(player, deck);
                }
            }
        }
    }

//      Dealer logic

    public void dealerMove() {
        while (dealer.checkShouldTwist(dealer.getHandValue())) {
            dealer.dealCard(dealer, deck);
        }
    }


    public String checkWinner(ArrayList<Participant> players) {
        Participant player1 = players.get(0);
        Participant player2 = players.get(1);

        if (!player1.getHand().checkBust() && !player2.getHand().checkBust()) {
            if (player1.getHandValue() == player2.getHandValue()) {
                return player1.getName();
            } else {
                return player1.getHandValue() > player2.getHandValue() ? player1.getName() : player2.getName();
            }

        } else {

            return player1.getHand().checkBust() ? player2.getName() : player1.getName();

        }
    }

    public String displayWinner(){
        return checkWinner(players);
    }

}

