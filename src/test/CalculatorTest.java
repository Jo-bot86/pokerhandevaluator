package test;

import static de.pokerhandevaluator.hand.card.CardSuit.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;

import org.junit.jupiter.api.Test;

import de.pokerhandevaluator.Calculator;
import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;

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
		calculator = new Calculator(hand1,hand2);
		calculator.calculate();
	}
	
	@Test
	public void calculateTest_hand1TwoPairs_hand2HighCard() {
		hand1 = new Hand(new Card(DIAMONDS, ACE), new Card(HEARTS, TWO),
				new Card(HEARTS, KING), new Card(DIAMONDS, KING), new Card(SPADES, ACE));
		hand2 = new Hand(new Card(SPADES, FOUR), new Card(SPADES, TWO),
				new Card(DIAMONDS, THREE), new Card(DIAMONDS, TEN), new Card(SPADES, NINE));
		calculator = new Calculator(hand1,hand2);
		calculator.calculate();
	}
	
}
