package de.pokerhandevaluator;

import static de.pokerhandevaluator.hand.card.CardValue.*;

import java.util.List;

import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;
import de.pokerhandevaluator.hand.card.CardValue;

/**
 * Represents the Calculator for determine the better of two poker hands.
 * 
 * @author Josef Weldemariam
 *
 */
public class Calculator {

	/**
	 * Contains one of the poker hands
	 */
	private Hand hand1;

	/**
	 * Contains the other one of the poker hands
	 */
	private Hand hand2;

	/**
	 * Creates an Calculator based on two given poker hands
	 * 
	 * @param hand1 List of Card objects defining the first poker hand
	 * @param hand2 List of Card objects defining the second poker hand
	 */
	public Calculator(Hand hand1, Hand hand2) {
		this.hand1 = hand1;
		this.hand2 = hand2;
	}
	
	public Hand getHand1() {
		return hand1;
	}

	public void setHand1(Hand hand1) {
		this.hand1 = hand1;
	}

	public Hand getHand2() {
		return hand2;
	}

	public void setHand2(Hand hand2) {
		this.hand2 = hand2;
	}

	@Override
	public String toString() {
		return "Calculator [hand1=" + hand1 + ", hand2=" + hand2 + "]";
	}

}
