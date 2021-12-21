package test.hand;

import static de.pokerhandevaluator.hand.card.CardSuit.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
	public void containsStraightTest_Street10ToAce_Orderd() {
		hand = new Hand(new Card(DIAMONDS, TEN), new Card(HEARTS, JACK),
				new Card(CLUBS, QUEEN), new Card(SPADES, KING), new Card(HEARTS, ACE));
		assertEquals(true, hand.containsStraight());
	}

	@Test
	public void containsStraightTest_Street10ToAce_Unorderd() {
		hand = new Hand(new Card(SPADES, KING), new Card(HEARTS, JACK),
				new Card(HEARTS, ACE), new Card(CLUBS, QUEEN), new Card(DIAMONDS, TEN));
		assertEquals(true, hand.containsStraight());
	}

	@Test
	public void containsStraightTest_Street10ToAce_OrderReversed() {
		hand = new Hand(new Card(HEARTS, ACE), new Card(SPADES, KING),
				new Card(CLUBS, QUEEN), new Card(HEARTS, JACK), new Card(DIAMONDS, TEN));
		assertEquals(true, hand.containsStraight());
	}

	@Test
	public void containsStraightTest_Street2To6_Ordered() {
		hand = new Hand(new Card(DIAMONDS, TWO), new Card(HEARTS, THREE),
				new Card(CLUBS, FOUR), new Card(SPADES, FIVE), new Card(HEARTS, SIX));
		assertEquals(true, hand.containsStraight());
	}

	@Test
	public void containsStraightTest_Street2To6_Unordered() {
		hand = new Hand(new Card(DIAMONDS, TWO), new Card(HEARTS, SIX),
				new Card(HEARTS, THREE), new Card(SPADES, FIVE), new Card(CLUBS, FOUR));
		assertEquals(true, hand.containsStraight());
	}

	@Test
	public void containsStraightTest_NoStreet_OneMissing() {
		hand = new Hand(new Card(HEARTS, TWO), new Card(CLUBS, QUEEN),
				new Card(HEARTS, JACK), new Card(SPADES, KING), new Card(DIAMONDS, TEN));
		assertEquals(false, hand.containsStraight());
	}

//	*******************************************************************************************
//  Contains four of a kind test

	@Test
	public void containsNumberOfAKindTest_Four_Kings_FirstFour() {
		hand = new Hand(new Card(HEARTS, KING), new Card(CLUBS, KING),
				new Card(SPADES, KING), new Card(DIAMONDS, KING), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(4);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Four_Kings_FirstThreeAndLast() {
		hand = new Hand(new Card(CLUBS, KING), new Card(HEARTS, KING),
				new Card(SPADES, KING), new Card(HEARTS, JACK), new Card(DIAMONDS, KING));
		Card card = hand.containsNumberOfAKind(4);
		assertNotNull(card);
		assertEquals(CLUBS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Four_Kings_FirstTwoAndLastTwo() {
		hand = new Hand(new Card(HEARTS, KING), new Card(CLUBS, KING),
				new Card(HEARTS, JACK), new Card(SPADES, KING), new Card(DIAMONDS, KING));
		Card card = hand.containsNumberOfAKind(4);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Four_Kings_FirstAndLastThree() {
		hand = new Hand(new Card(HEARTS, KING), new Card(HEARTS, JACK),
				new Card(CLUBS, KING), new Card(SPADES, KING), new Card(DIAMONDS, KING));
		Card card = hand.containsNumberOfAKind(4);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Four_Kings_LastFour() {
		hand = new Hand(new Card(HEARTS, JACK), new Card(HEARTS, KING),
				new Card(CLUBS, KING), new Card(SPADES, KING), new Card(DIAMONDS, KING));
		Card card = hand.containsNumberOfAKind(4);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Three_Kings_InsteadOfFour() {
		hand = new Hand(new Card(HEARTS, TWO), new Card(CLUBS, KING),
				new Card(HEARTS, JACK), new Card(SPADES, KING), new Card(DIAMONDS, KING));
		Card card = hand.containsNumberOfAKind(4);
		assertNull(card);		
	}

//	*******************************************************************************************
//  Contains three of a kind test

	@Test
	public void containsNumberOfAKindTest_Three_Kings_FirstThree() {
		hand = new Hand(new Card(HEARTS, KING), new Card(CLUBS, KING),
				new Card(SPADES, KING), new Card(DIAMONDS, QUEEN),
				new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(3);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Three_Kings_FirstTwoAndFourth() {
		hand = new Hand(new Card(HEARTS, KING), new Card(SPADES, KING),
				new Card(DIAMONDS, QUEEN), new Card(CLUBS, KING), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(3);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Three_Kings_FirstTwoAndFifth() {
		hand = new Hand(new Card(HEARTS, KING), new Card(SPADES, KING),
				new Card(DIAMONDS, QUEEN), new Card(HEARTS, JACK), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(3);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Three_Kings_FirstThirdAndFifth() {
		hand = new Hand(new Card(HEARTS, KING), new Card(DIAMONDS, QUEEN),
				new Card(SPADES, KING), new Card(HEARTS, JACK), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(3);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Three_Kings_FirstAndLastTwo() {
		hand = new Hand(new Card(HEARTS, KING), new Card(DIAMONDS, QUEEN),
				new Card(HEARTS, JACK), new Card(SPADES, KING), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(3);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Three_Kings_SecondAndLastTwo() {
		hand = new Hand(new Card(DIAMONDS, QUEEN), new Card(HEARTS, KING),
				new Card(HEARTS, JACK), new Card(SPADES, KING), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(3);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Three_Kings_LastThree() {
		hand = new Hand(new Card(DIAMONDS, QUEEN), new Card(HEARTS, JACK),
				new Card(HEARTS, KING), new Card(SPADES, KING), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(3);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}
	
	@Test
	public void containsNumberOfAKindTest_Two_Kings_InsteadOfThree() {
		hand = new Hand(new Card(DIAMONDS, QUEEN), new Card(HEARTS, JACK),
				new Card(HEARTS, QUEEN), new Card(SPADES, KING), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(3);
		assertNull(card);
	}

//	*****************************************************************************************
//  Contains two of a kind test

	@Test
	public void containsNumberOfAKindTest_Two_Kings_FirstTwo() {
		hand = new Hand(new Card(HEARTS, KING), new Card(CLUBS, KING),
				new Card(SPADES, QUEEN), new Card(DIAMONDS, TEN), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_FirstAndThird() {
		hand = new Hand(new Card(HEARTS, KING), new Card(SPADES, QUEEN),
				new Card(CLUBS, KING), new Card(DIAMONDS, TEN), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_FirstAndFourth() {
		hand = new Hand(new Card(HEARTS, KING), new Card(SPADES, QUEEN),
				new Card(DIAMONDS, QUEEN), new Card(CLUBS, KING), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_FirstAndLast() {
		hand = new Hand(new Card(HEARTS, KING), new Card(SPADES, QUEEN),
				new Card(DIAMONDS, TEN), new Card(HEARTS, JACK), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_SecondAndLast() {
		hand = new Hand(new Card(SPADES, QUEEN), new Card(HEARTS, KING),
				new Card(DIAMONDS, TEN), new Card(HEARTS, JACK), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_ThirdAndLast() {
		hand = new Hand(new Card(SPADES, QUEEN), new Card(DIAMONDS, TEN),
				new Card(HEARTS, KING), new Card(HEARTS, JACK), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_FourthAndLast() {
		hand = new Hand(new Card(SPADES, QUEEN), new Card(DIAMONDS, TEN),
				new Card(HEARTS, JACK), new Card(HEARTS, KING), new Card(CLUBS, KING));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_SecondAndThird() {
		hand = new Hand(new Card(SPADES, QUEEN), new Card(HEARTS, KING),
				new Card(CLUBS, KING), new Card(DIAMONDS, TEN), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_SecondAndFourth() {
		hand = new Hand(new Card(SPADES, QUEEN), new Card(HEARTS, KING),
				new Card(DIAMONDS, TEN), new Card(CLUBS, KING), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(HEARTS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}

	@Test
	public void containsNumberOfAKindTest_Two_Kings_ThirdAndFourth() {
		hand = new Hand(new Card(SPADES, QUEEN), new Card(DIAMONDS, TEN),
				new Card(CLUBS, KING), new Card(HEARTS, KING), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(2);
		assertNotNull(card);
		assertEquals(CLUBS, card.getCardSuit());
		assertEquals(KING, card.getCardValue());
	}
	
	@Test
	public void containsNumberOfAKindTest_Two_Kings_NoPair() {
		hand = new Hand(new Card(SPADES, QUEEN), new Card(DIAMONDS, TEN),
				new Card(CLUBS, KING), new Card(HEARTS, FOUR), new Card(HEARTS, JACK));
		Card card = hand.containsNumberOfAKind(2);
		assertNull(card);
	}
	
// 	*********************************************************************************************
	
	@Test
	public void containsFullHouseTest_TwoAces_ThreeTens() {
		hand = new Hand(new Card(SPADES, KING), new Card(DIAMONDS, TEN),
				new Card(CLUBS, TEN), new Card(HEARTS, KING), new Card(HEARTS, TEN));
		assertEquals(true, hand.containsFullHouse());
	}
	
	@Test
	public void containsFullHouseTest_TwoFours_ThreeTwos() {
		hand = new Hand(new Card(SPADES, TWO), new Card(DIAMONDS, FOUR),
				new Card(CLUBS, FOUR), new Card(HEARTS, TWO), new Card(CLUBS, TWO));
		assertEquals(true, hand.containsFullHouse());
	}
	
	@Test
	public void containsFullHouseTest_ThreeQueens() {
		hand = new Hand(new Card(SPADES, TWO), new Card(DIAMONDS, QUEEN),
				new Card(CLUBS, QUEEN), new Card(HEARTS, KING), new Card(HEARTS, QUEEN));
		assertEquals(false, hand.containsFullHouse());
	}
	
	@Test
	public void containsFullHouseTest_TwoJacks_TwoNines() {
		hand = new Hand(new Card(SPADES, JACK), new Card(DIAMONDS, NINE),
				new Card(CLUBS, NINE), new Card(HEARTS, JACK), new Card(CLUBS, TWO));
		assertEquals(false, hand.containsFullHouse());
	}
	
}
