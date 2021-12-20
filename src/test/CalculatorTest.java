package test;

import static de.pokerhandevaluator.hand.card.CardSuit.CLUBS;
import static de.pokerhandevaluator.hand.card.CardSuit.DIAMONDS;
import static de.pokerhandevaluator.hand.card.CardValue.TEN;
import static de.pokerhandevaluator.hand.card.CardValue.TWO;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.pokerhandevaluator.Calculator;
import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;

public class CalculatorTest {
	Hand HAND1;
	Hand HAND2;
	Calculator calc;
	
	@Before
	public void init() {
		HAND1 = new Hand(new Card(CLUBS, TWO), new Card(CLUBS, TWO), new Card(CLUBS, TWO),
				new Card(CLUBS, TWO), new Card(CLUBS, TWO));
	    HAND2 = new Hand(new Card(DIAMONDS, TEN), new Card(CLUBS, TWO), new Card(CLUBS, TWO),
				new Card(CLUBS, TWO), new Card(CLUBS, TWO));
	    calc = new Calculator(HAND1, HAND2);
	}
	
	
}
