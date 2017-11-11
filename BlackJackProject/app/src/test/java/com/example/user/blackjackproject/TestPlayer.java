package com.example.user.blackjackproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 10/11/2017.
 */

public class TestPlayer {

    Player player;
    Card card;
    Card card2;
    Card card3;

    @Before
    public void setup(){
        player = new Player("Ally", 1000);
        card = new Card(Suit.DIAMONDS, Rank.TWO);
        card2 = new Card(Suit.SPADES, Rank.TEN);
        card3 = new Card(Suit.HEARTS, Rank.TEN);

    }

    @Test
    public void playerHasName(){
        assertEquals("Ally", player.getName());
    }

    @Test
    public void playerCanTakeCard(){
        player.addCardToHand(card);
        assertEquals(1, player.getHand().getNumberOfCards());
    }

    @Test
    public void playerHandValueIs11(){
        player.addCardToHand(card);
        player.addCardToHand(card2);
        assertEquals(12, player.getHandValue());
    }

    @Test
    public void playerGoesBust() throws Exception {
        player.addCardToHand(card);
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        assertEquals(true, player.getHand().checkBust());

    }

    @Test
    public void playerGetStickOrTwist() throws Exception {
        assertEquals("t", player.getStickOrTwist());
    }

    @Test
    public void playerSetStickOrTwist() throws Exception {
        player.setStickOrTwist("s");
        assertEquals("s", player.getStickOrTwist());
    }

    @Test
    public void setFunds() throws Exception {
        player.setFunds(50);
        assertEquals(50, player.getFunds());
    }
}
