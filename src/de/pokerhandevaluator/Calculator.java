package de.pokerhandevaluator;

import static de.pokerhandevaluator.hand.HandRanking.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;

import java.util.List;
import java.util.stream.Collectors;

import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.HandRanking;
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

	public Calculator() {

	}

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
	 * Determines the winner of a poker round and prints the HandRanking to the
	 * console
	 */
	public void calculate() {
		if (hand1.getCurrentHandRanking().compareTo(hand2.getCurrentHandRanking()) < 0) {
			System.out.println("Hand 2 wins with " + hand2.getCurrentHandRanking());
		} else if (hand1.getCurrentHandRanking()
				.compareTo(hand2.getCurrentHandRanking()) > 0) {
			System.out.println("Hand 1 wins with " + hand1.getCurrentHandRanking());
		} else {
			compareHands();
		}
	}

	/**
	 * Compares two hands with the same HandRanking value
	 */
	public void compareHands() {
		if (hand1.getCurrentHandRanking().compareTo(ROYAL_FLUSH) == 0) {
			System.out.println("Split Pot " + hand1.getCurrentHandRanking());
		}
		if (hand1.getCurrentHandRanking().compareTo(STRAIGHT_FLUSH) == 0) {
			compareStraightFlush();
		}
		if (hand1.getCurrentHandRanking().compareTo(FOUR_OF_A_KIND) == 0) {
			compareFourOfAKind();
		}
	}

	/**
	 * Compares two hands with the same ranking and determines a winner. If both
	 * hands contains a four of a kind with the same card value, then the value of
	 * the highest card is determined and this is compared
	 */
	public void compareFourOfAKind() {
		if (hand1.containsThreeOrFourOfAKind(4).getCardValue()
				.compareTo(hand2.containsThreeOrFourOfAKind(4).getCardValue()) < 0) {
			System.out.println("Hand 2 wins with " + hand2.getCurrentHandRanking());
		} else if (hand1.containsThreeOrFourOfAKind(4).getCardValue()
				.compareTo(hand2.containsThreeOrFourOfAKind(4).getCardValue()) > 0) {
			System.out.println("Hand 1 wins with " + hand1.getCurrentHandRanking());
		} else {
			CardValue cardValueHand1 = getHighCard(hand1);
			CardValue cardValueHand2 = getHighCard(hand2);
			compareHighCard(cardValueHand1, cardValueHand2);
		}
	}

	/**
	 * Compares the value of two given card values and determines a winner
	 * 
	 * @param cardValueHand1 card value of a high card from hand1
	 * @param cardValueHand2 card value of a high card from hand2
	 */
	public void compareHighCard(CardValue cardValueHand1, CardValue cardValueHand2) {
		if (cardValueHand1.compareTo(cardValueHand2) < 0) {
			System.out.println("Hand 2 wins with " + hand2.getCurrentHandRanking());
		} else if (cardValueHand1.compareTo(cardValueHand2) > 0) {
			System.out.println("Hand 1 wins with " + hand1.getCurrentHandRanking());
		} else {
			System.out.println("Split Pot " + hand1.getCurrentHandRanking());
		}
	}

	/**
	 * Determines the winner of two hands with a straight flush
	 */
	public void compareStraightFlush() {
		if (hand1.getHighestCard().getCardValue()
				.compareTo(hand2.getHighestCard().getCardValue()) < 0) {
			System.out.println("Hand 2 wins with " + hand2.getCurrentHandRanking());
		} else if (hand1.getHighestCard().getCardValue()
				.compareTo(hand2.getHighestCard().getCardValue()) > 0) {
			System.out.println("Hand 1 wins with " + hand1.getCurrentHandRanking());
		} else {
			System.out.println("Split Pot " + hand1.getCurrentHandRanking());
		}
	}

	/**
	 * Determines the value of the highcard that are not part of a HandRanking
	 * higher then HighCard. For example if a hand has e.g. a two pair the last
	 * remaining card value is determined. If a hand contains a three of a kind then
	 * the higher of the two last card values is determined.
	 * 
	 * @param hand
	 * @return value of the highcard that are not part of a HandRanking higher then
	 *         HighCard
	 */
	public CardValue getHighCard(Hand hand) {
		// map every card to it's card value
		List<CardValue> cardValueList = hand.getCurrentHand().stream()
				.map(card -> card.getCardValue()).collect(Collectors.toList());
		// remove duplicates
		List<CardValue> listWithoutDuplicates = cardValueList.stream().distinct()
				.collect(Collectors.toList());

		if (hand.getCurrentHandRanking().compareTo(FULL_HOUSE) == 0) {
//			TODO
		}

		if (hand.getCurrentHandRanking().compareTo(FOUR_OF_A_KIND) == 0) {
			Card fourOfAKindCard = hand.containsThreeOrFourOfAKind(4);
			for (CardValue value : listWithoutDuplicates) {
				if (fourOfAKindCard.getCardValue().compareTo(value) == 0) {
					listWithoutDuplicates.remove(value);
				}
			}
			return listWithoutDuplicates.get(0);
		}

		if (hand.getCurrentHandRanking().compareTo(TWO_PAIR) == 0) {
			List<Card> twoPairCards = hand.containsPair();
			for (int i = 0; i < listWithoutDuplicates.size(); i++) {
				if (twoPairCards.get(0).getCardValue()
						.compareTo(listWithoutDuplicates.get(i)) == 0) {
					listWithoutDuplicates.remove(i);
				}
				if (twoPairCards.get(1).getCardValue()
						.compareTo(listWithoutDuplicates.get(i)) == 0) {
					listWithoutDuplicates.remove(i);
				}
			}
			return listWithoutDuplicates.get(0);
		}

		if (hand.getCurrentHandRanking().compareTo(THREE_OF_A_KIND) == 0) {
			Card fourOfAKindCard = hand.containsThreeOrFourOfAKind(3);
			for (int i = 0; i < listWithoutDuplicates.size(); i++) {
				if (fourOfAKindCard.getCardValue()
						.compareTo(listWithoutDuplicates.get(i)) == 0) {
					listWithoutDuplicates.remove(i);
				}
			}
			if (listWithoutDuplicates.get(0)
					.compareTo(listWithoutDuplicates.get(1)) < 0) {
				return listWithoutDuplicates.get(1);
			} else {
				return listWithoutDuplicates.get(0);
			}
		}

		if (hand.getCurrentHandRanking().compareTo(PAIR) == 0) {
//			TODO
		}

		return null;
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
