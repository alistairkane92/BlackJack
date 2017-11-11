package com.example.user.blackjackproject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by user on 10/11/2017.
 */

public class TestDeck {
    private Deck deck;
    private Deck spyDeck;

    @Before
    public void setup(){
        deck = new Deck();
        spyDeck = Mockito.spy(deck);
    }


    @Test
    public void deckHas52Cards(){
        assertEquals(52, deck.getNumberOfCards());
    }

    @Test
    public void deckIsShuffled(){
        Deck deck2 = new Deck();
        assertFalse(deck.equals(deck2));
    }



    @Test
    public void canGetCard() {
        Mockito.when(spyDeck.getCard()).thenReturn(new Card(Suit.DIAMONDS, Rank.FIVE));
        Card card = spyDeck.getCard();
        assertEquals(Suit.DIAMONDS, card.getSuit());
        assertEquals(Rank.FIVE, card.getRank());
        assertEquals(5, card.getValue());
    }
}
