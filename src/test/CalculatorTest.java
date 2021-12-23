package test;

import static de.pokerhandevaluator.hand.card.CardSuit.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.pokerhandevaluator.Calculator;
import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;
import de.pokerhandevaluator.hand.card.CardValue;

public class CalculatorTest {
	Hand hand1;
	Hand hand2;
	Calculator calculator;

	@Test
	public void calculateTest_hand1Flush_hand2RoyalFlush() {
		hand1 = new Hand(new Card(HEARTS, KING), new Card(HEARTS, SEVEN),
				new Card(HEARTS, FIVE), new Card(HEARTS, TWO), new Card(HEARTS, ACE));
		hand2 = new Hand(new Card(SPADES, JACK), new Card(SPADES, ACE),
				new Card(SPADES, QUEEN), new Card(SPADES, TEN), new Card(SPADES, KING));
		calculator = new Calculator(hand1, hand2);
		System.out.println(calculator.calculate());
	}

	@Test
	public void calculateTest_hand1TwoPairs_hand2HighCard() {
		hand1 = new Hand(new Card(DIAMONDS, ACE), new Card(HEARTS, TWO),
				new Card(HEARTS, KING), new Card(DIAMONDS, KING), new Card(SPADES, ACE));
		hand2 = new Hand(new Card(SPADES, FOUR), new Card(SPADES, TWO),
				new Card(DIAMONDS, THREE), new Card(DIAMONDS, TEN),
				new Card(SPADES, NINE));
		calculator = new Calculator(hand1, hand2);
		System.out.println(calculator.calculate());
	}

// *********************************************************************************************

	@Test
	public void compareHighCardsListTest_hand1HighCardTen_hand2_HighCardJack() {
		hand1 = new Hand(new Card(HEARTS, TWO), new Card(SPADES, FOUR),
				new Card(CLUBS, TEN), new Card(HEARTS, FIVE), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(SPADES, JACK), new Card(SPADES, TWO),
				new Card(SPADES, THREE), new Card(DIAMONDS, EIGHT),
				new Card(SPADES, NINE));
		calculator = new Calculator(hand1, hand2);
		
		List<CardValue> cardValueshand1 = calculator.mapToCardValue(hand1.getCurrentHand());
		List<CardValue> cardValueshand2 = calculator.mapToCardValue(hand2.getCurrentHand());
		
		List<CardValue> withoutDuplicates1 = calculator.removeDuplicates(cardValueshand1);
		List<CardValue> withoutDuplicates2 = calculator.removeDuplicates(cardValueshand2);

		assertEquals("Hand 2 wins with HIGH_CARD", calculator.compareHighCardsList(withoutDuplicates1, withoutDuplicates2));
		
	}
	
	@Test
	public void compareHighCardsListTest_hand1HighCardKing_hand2_HighCardQueen() {
		hand1 = new Hand(new Card(HEARTS, JACK), new Card(SPADES, FOUR),
				new Card(CLUBS, TEN), new Card(HEARTS, KING), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(SPADES, JACK), new Card(SPADES, QUEEN),
				new Card(SPADES, THREE), new Card(DIAMONDS, EIGHT),
				new Card(SPADES, NINE));
		calculator = new Calculator(hand1, hand2);
		
		List<CardValue> cardValueshand1 = calculator.mapToCardValue(hand1.getCurrentHand());
		List<CardValue> cardValueshand2 = calculator.mapToCardValue(hand2.getCurrentHand());
		
		List<CardValue> withoutDuplicates1 = calculator.removeDuplicates(cardValueshand1);
		List<CardValue> withoutDuplicates2 = calculator.removeDuplicates(cardValueshand2);

		assertEquals("Hand 1 wins with HIGH_CARD", calculator.compareHighCardsList(withoutDuplicates1, withoutDuplicates2));
		
	}

}
