package com.example.user.blackjackproject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by user on 10/11/2017.
 */

public class TestGame {

    private Game game;
    private Deck deck;
    private Deck spyDeck;
    private Dealer player2;
    private Player player1;
    private ArrayList<Participant> players;
    private Ui ui;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @Before
    public void before(){
        deck = new Deck();
        spyDeck = Mockito.spy(deck);
        player1 = new Player("Player 1");
        player2 = new Dealer("Dealer");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        ui = mock(Ui.class);
        game = new Game(spyDeck, ui);
        card1 = new Card(Suit.DIAMONDS, Rank.FOUR);
        card2 = new Card(Suit.CLUBS, Rank.NINE);
        card3 = new Card(Suit.HEARTS, Rank.SEVEN);
        card4 = new Card(Suit.SPADES, Rank.NINE);
    }

//    @Test
//    public void gameCanStart(){
//        game.play();
//        assertEquals(2, game.getPlayers().get(0).getHand().getNumberOfCards());
//    }

    @Test
    public void gameHasWinner(){
        player1.addCardToHand(card1);
        player1.addCardToHand(card2);
        player2.addCardToHand(card3);
        player2.addCardToHand(card4);
        Participant player = game.checkWinner(players);
        assertEquals("Dealer", player.getName());
    }

    @Test
    public void gameHasWinnerWhenPlayerBust() throws Exception {
        player1.addCardToHand(card1);
        player1.addCardToHand(card2);
        player2.addCardToHand(card3);
        player2.addCardToHand(card4);
        player2.addCardToHand(card4);
        player2.addCardToHand(card4);
        Participant player = game.checkWinner(players);
        assertEquals("Player 1", player.getName());
    }



//    @Test
//    public void testDealerTwistsWhenUnder16() throws Exception {
//
//    }
}
