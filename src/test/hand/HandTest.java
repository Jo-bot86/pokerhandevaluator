package test.hand;

import static de.pokerhandevaluator.hand.card.CardSuit.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;

public class HandTest {
	Hand hand;
	
	@Test
	public void getHighestCardTest_Ace_At_First_Position() {
		hand = new Hand(new Card(CLUBS, ACE), new Card(CLUBS, TWO), new Card(CLUBS, THREE),
				new Card(CLUBS, TEN), new Card(DIAMONDS, KING));
		
		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(CLUBS, hand.getHighestCard().getCardSuit());
	}
	
	@Test
	public void getHighestCardTest_Ace_At_Last_Position() {
		hand = new Hand(new Card(CLUBS, FIVE), new Card(CLUBS, TWO), new Card(CLUBS, THREE),
				new Card(CLUBS, TEN), new Card(DIAMONDS, ACE));
		
		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(DIAMONDS, hand.getHighestCard().getCardSuit());
	}
	
	@Test
	public void getHighestCardTest_Ace_At_Middle_Position() {
		hand = new Hand(new Card(CLUBS, FIVE), new Card(CLUBS, TWO), new Card(HEARTS, ACE),
				new Card(CLUBS, TEN), new Card(DIAMONDS, QUEEN));
		
		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(HEARTS, hand.getHighestCard().getCardSuit());
	}
	
	@Test
	public void getHighestCardTest_Ace_At_FirstANDLast_Position() {
		hand = new Hand(new Card(CLUBS, ACE), new Card(SPADES, FOUR), new Card(DIAMONDS, SEVEN),
				new Card(CLUBS, JACK), new Card(DIAMONDS, ACE));
		
		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(CLUBS, hand.getHighestCard().getCardSuit());
	}
	
	
}
