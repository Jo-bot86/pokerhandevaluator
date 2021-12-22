package de.pokerhandevaluator;

import static de.pokerhandevaluator.hand.HandRanking.*;
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
	
	/**
	 * Sets the ranking for a given Hand object
	 * @param hand
	 */
	public void setHandRanking(Hand hand) {
		if(hand.getHighestCard().getCardValue().compareTo(ACE) == 0 && hand.containsFlush() && hand.containsStraight()) {
			hand.setCurrentHandRanking(ROYAL_FLUSH);
			return;
		}
		if(hand.containsFlush() && hand.containsStraight()) {
			hand.setCurrentHandRanking(STRAIGHT_FLUSH);
			return;
		}
		if(hand.containsThreeOrFourOfAKind(4) != null) {
			hand.setCurrentHandRanking(FOUR_OF_A_KIND);
			return;
		}
		if(hand.containsFullHouse()) {
			hand.setCurrentHandRanking(FULL_HOUSE);
			return;
		}
		if(hand.containsFlush()) {
			hand.setCurrentHandRanking(FLUSH);
			return;
		}
		if(hand.containsStraight()) {
			hand.setCurrentHandRanking(STRAIGHT);
			return;
		}
		if(hand.containsThreeOrFourOfAKind(3) != null) {
			hand.setCurrentHandRanking(THREE_OF_A_KIND);
			return;
		}
		if(hand.containsPair().size() == 2) {
			hand.setCurrentHandRanking(TWO_PAIR);
			return;
		}
		if(hand.containsPair().size() == 1) {
			hand.setCurrentHandRanking(ONE_PAIR);
			return;
		}
		hand.setCurrentHandRanking(HIGH_CARD);
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
