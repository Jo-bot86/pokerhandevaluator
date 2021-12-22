package test;

import static de.pokerhandevaluator.hand.card.CardSuit.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.pokerhandevaluator.Calculator;
import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;

public class CalculatorTest {
	Hand hand1;
	Hand hand2;
	Calculator calculator;
	
//	@Test
//	public void calculateTest_hand1Flush_hand2RoyalFlush() {
//		hand1 = new Hand(new Card(HEARTS, KING), new Card(HEARTS, SEVEN),
//				new Card(HEARTS, FIVE), new Card(HEARTS, TWO), new Card(HEARTS, ACE));
//		hand2 = new Hand(new Card(SPADES, JACK), new Card(SPADES, ACE),
//				new Card(SPADES, QUEEN), new Card(SPADES, TEN), new Card(SPADES, KING));
//		calculator = new Calculator(hand1,hand2);
//		calculator.calculate();
//	}
//	
//	@Test
//	public void calculateTest_hand1TwoPairs_hand2HighCard() {
//		hand1 = new Hand(new Card(DIAMONDS, ACE), new Card(HEARTS, TWO),
//				new Card(HEARTS, KING), new Card(DIAMONDS, KING), new Card(SPADES, ACE));
//		hand2 = new Hand(new Card(SPADES, FOUR), new Card(SPADES, TWO),
//				new Card(DIAMONDS, THREE), new Card(DIAMONDS, TEN), new Card(SPADES, NINE));
//		calculator = new Calculator(hand1,hand2);
//		calculator.calculate();
//	}
	
// *********************************************************************************************
	
	@Test
	public void getHighCardTest_FourOfAKindAndAce() {
		hand1 = new Hand(new Card(HEARTS, FOUR), new Card(SPADES, FOUR),
				new Card(DIAMONDS, FOUR), new Card(HEARTS, ACE), new Card(CLUBS, FOUR));
		calculator = new Calculator();
		assertEquals(ACE, calculator.getHighCard(hand1));
	}
	
	@Test
	public void getHighCardTest_FourOfAKindAndTwo() {
		hand1 = new Hand(new Card(HEARTS, FOUR), new Card(SPADES, FOUR),
				new Card(DIAMONDS, FOUR), new Card(HEARTS, TWO), new Card(CLUBS, FOUR));
		calculator = new Calculator();
		assertEquals(TWO, calculator.getHighCard(hand1));
	}
	
	@Test
	public void getHighCardTest_TwoPairsAndAce() {
		hand1 = new Hand(new Card(HEARTS, FOUR), new Card(SPADES, FOUR),
				new Card(DIAMONDS, SIX), new Card(HEARTS, ACE), new Card(CLUBS, SIX));
		calculator = new Calculator();
		assertEquals(ACE, calculator.getHighCard(hand1));
	}
	
	@Test
	public void getHighCardTest_TwoPairsAndTwo() {
		hand1 = new Hand(new Card(HEARTS, FOUR), new Card(SPADES, FOUR),
				new Card(DIAMONDS, SIX), new Card(HEARTS, TWO), new Card(CLUBS, SIX));
		calculator = new Calculator();
		assertEquals(TWO, calculator.getHighCard(hand1));
	}
	
	@Test
	public void getHighCardTest_ThreeOfAKindAndQueenAndJack() {
		hand1 = new Hand(new Card(HEARTS, FOUR), new Card(SPADES, FOUR),
				new Card(DIAMONDS, FOUR), new Card(HEARTS, QUEEN), new Card(CLUBS, JACK));
		calculator = new Calculator();
		assertEquals(QUEEN, calculator.getHighCard(hand1));
	}
	
	@Test
	public void getHighCardTest_ThreeOfAKindAndTwoAndEight() {
		hand1 = new Hand(new Card(HEARTS, KING), new Card(SPADES, KING),
				new Card(DIAMONDS, TWO), new Card(HEARTS, EIGHT), new Card(CLUBS, KING));
		calculator = new Calculator();
		assertEquals(EIGHT, calculator.getHighCard(hand1));
	}
	
	
	
}
