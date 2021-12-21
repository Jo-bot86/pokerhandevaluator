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
		hand = new Hand(new Card(CLUBS, ACE), new Card(CLUBS, TWO),
				new Card(CLUBS, THREE), new Card(CLUBS, TEN), new Card(DIAMONDS, KING));

		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(CLUBS, hand.getHighestCard().getCardSuit());
	}

	@Test
	public void getHighestCardTest_Ace_At_Last_Position() {
		hand = new Hand(new Card(CLUBS, FIVE), new Card(CLUBS, TWO),
				new Card(CLUBS, THREE), new Card(CLUBS, TEN), new Card(DIAMONDS, ACE));

		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(DIAMONDS, hand.getHighestCard().getCardSuit());
	}

	@Test
	public void getHighestCardTest_Ace_At_Middle_Position() {
		hand = new Hand(new Card(CLUBS, FIVE), new Card(CLUBS, TWO),
				new Card(HEARTS, ACE), new Card(CLUBS, TEN), new Card(DIAMONDS, QUEEN));
		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(HEARTS, hand.getHighestCard().getCardSuit());
	}

	@Test
	public void getHighestCardTest_Ace_At_FirstANDLast_Position() {
		hand = new Hand(new Card(CLUBS, ACE), new Card(SPADES, FOUR),
				new Card(DIAMONDS, SEVEN), new Card(CLUBS, JACK),
				new Card(DIAMONDS, ACE));

		assertEquals(ACE, hand.getHighestCard().getCardValue());
		assertEquals(CLUBS, hand.getHighestCard().getCardSuit());
	}

//	*****************************************************************************************************

	@Test
	public void containsFlushTest_FlushOfClubs() {
		hand = new Hand(new Card(CLUBS, TWO), new Card(CLUBS, FOUR),
				new Card(CLUBS, SEVEN), new Card(CLUBS, JACK), new Card(CLUBS, ACE));
		assertEquals(true, hand.containsFlush());
	}

	@Test
	public void containsFlushTest_FlushOfHearts() {
		hand = new Hand(new Card(HEARTS, TWO), new Card(HEARTS, FOUR),
				new Card(HEARTS, SEVEN), new Card(HEARTS, JACK), new Card(HEARTS, ACE));
		assertEquals(true, hand.containsFlush());
	}

	@Test
	public void containsFlushTest_OneCardMissing() {
		hand = new Hand(new Card(DIAMONDS, TWO), new Card(HEARTS, FOUR),
				new Card(HEARTS, SEVEN), new Card(HEARTS, JACK), new Card(HEARTS, ACE));
		assertEquals(false, hand.containsFlush());
	}

	@Test
	public void containsFlushTest_TwoCardsOfSameSuits() {
		hand = new Hand(new Card(DIAMONDS, TWO), new Card(HEARTS, FOUR),
				new Card(CLUBS, SEVEN), new Card(SPADES, JACK), new Card(HEARTS, ACE));
		assertEquals(false, hand.containsFlush());
	}

//	*********************************************************************************************************

	@Test
	public void containsStreetTest_Street10ToAce_Orderd() {
		hand = new Hand(new Card(DIAMONDS, TEN), new Card(HEARTS, JACK),
				new Card(CLUBS, QUEEN), new Card(SPADES, KING), new Card(HEARTS, ACE));
		assertEquals(true, hand.containsStreet());
	}

	@Test
	public void containsStreetTest_Street10ToAce_Unorderd() {
		hand = new Hand(new Card(SPADES, KING), new Card(HEARTS, JACK),
				new Card(HEARTS, ACE), new Card(CLUBS, QUEEN), new Card(DIAMONDS, TEN));
		assertEquals(true, hand.containsStreet());
	}

	@Test
	public void containsStreetTest_Street10ToAce_OrderReversed() {
		hand = new Hand(new Card(HEARTS, ACE), new Card(SPADES, KING),
				new Card(CLUBS, QUEEN), new Card(HEARTS, JACK), new Card(DIAMONDS, TEN));
		assertEquals(true, hand.containsStreet());
	}
	
	public void containsStreetTest_Street2To6_Ordered() {
		hand = new Hand(new Card(DIAMONDS, TWO), new Card(HEARTS, THREE),
				new Card(CLUBS, FOUR), new Card(SPADES, FIVE), new Card(HEARTS, SIX));
		assertEquals(true, hand.containsStreet());
	}

	@Test
	public void containsStreetTest_NoStreet_OneMissing() {
		hand = new Hand(new Card(HEARTS, TWO), new Card(CLUBS, QUEEN),
				new Card(HEARTS, JACK), new Card(SPADES, KING), new Card(DIAMONDS, TEN));
		assertEquals(false, hand.containsStreet());
	}
}
