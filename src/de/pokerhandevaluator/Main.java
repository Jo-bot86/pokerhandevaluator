package de.pokerhandevaluator;

import static de.pokerhandevaluator.hand.card.CardSuit.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;


import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;

/**
 * Starting point of the poker evaluator application. In the main method there
 * are two Calculator objects. calculator1 is called with HAND1 and HAND2 which
 * you can assign values to yourself. calculator2 gets the hands supplied by the
 * dealer object
 * 
 * @author Josef Weldemariam
 *
 */
public class Main {
	private static final Hand HAND1 = new Hand(new Card(HEARTS, KING),
			new Card(SPADES, SEVEN), new Card(HEARTS, FOUR), new Card(HEARTS, ACE),
			new Card(HEARTS, SEVEN));

	private static final Hand HAND2 = new Hand(new Card(CLUBS, JACK),
			new Card(DIAMONDS, SEVEN), new Card(SPADES, KING), new Card(CLUBS, SEVEN),
			new Card(SPADES, ACE));

	public static void main(String[] args) {
		Dealer dealer = new Dealer();
		final Hand hand1Random = dealer.getHands().get(0);
		final Hand hand2Random = dealer.getHands().get(1);

		Calculator calculator1 = new Calculator(hand1Random, hand2Random);

		System.out.println("Calculator 1");
		System.out.println("\nHand 1: " + hand1Random);
		System.out.println("\nHand 2: " + hand2Random);

		System.out.println("\n" + calculator1.calculate());

		
		Calculator calculator2 = new Calculator(HAND1, HAND2);

		System.out.println("\nCalculator 2");
		System.out.println("\nHand 1: " + HAND1);
		System.out.println("\nHand 2: " + HAND2);

		System.out.println("\n" + calculator2.calculate());
	}

}
