package de.pokerhandevaluator.hand;

import static de.pokerhandevaluator.hand.card.CardValue.*;
import static de.pokerhandevaluator.hand.HandRanking.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import de.pokerhandevaluator.hand.card.Card;

/**
 * Represents a hand in a poker game.
 * 
 * @author Josef Weldemariam
 *
 */
public class Hand {
	private final List<Card> currentHand;
	private HandRanking currentHandRanking;

	public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
		currentHand = List.of(card1, card2, card3, card4, card5);
	}

	public List<Card> getCurrentHand() {
		return currentHand;
	}

	public HandRanking getCurrentHandRanking() {
		return currentHandRanking;
	}

	public void setCurrentHandRanking(HandRanking ranking) {
		currentHandRanking = ranking;
	}

	/**
	 * Determines the card with the highest CardValue
	 * 
	 * @return the card from currentHand with the highest CardValue
	 */
	public Card getHighestCard() {
		Card highestCard = getCurrentHand().get(0);
		Card tmpCard;
		for (int i = 1; i < currentHand.size(); i++) {
			tmpCard = currentHand.get(i);
			if (tmpCard.getCardValue().compareTo(highestCard.getCardValue()) > 0) {
				highestCard = tmpCard;
			}
		}
		return highestCard;
	}

	/**
	 * Checks if currentHand contains a full house.
	 * 
	 * @return true only if currentHand contains a full house, otherwise false
	 */
	public boolean containsFullHouse() {
		Card pairCard = containsNumberOfAKind(2);
		Card threeOfAKindCard = containsNumberOfAKind(3);
		if (pairCard != null && threeOfAKindCard != null && pairCard.getCardValue()
				.compareTo(threeOfAKindCard.getCardValue()) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if currentHand contains a flush returns true if currentHand contains a
	 * flush otherwise false
	 * 
	 * @return true only if currentHand contains a flush, otherwise false
	 */
	public boolean containsFlush() {
		Card firstCard = getCurrentHand().get(0);
		List<Card> potentialFlushHand = currentHand.stream().filter(
				card -> card.getCardSuit().compareTo(firstCard.getCardSuit()) == 0)
				.collect(Collectors.toList());
		if (potentialFlushHand.size() == 5) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if currentHand contains number of kind where number is passed as
	 * parameter and can accept the values 2, 3 and 4. This method can be used for
	 * checking if currentHand contains four, three or two of a kind. The method
	 * returns null if no number of kind is found
	 * 
	 * Example 1: If you call containsNumberOfAKind(2) for [ACE,THREE,ACE,ACE,TEN]
	 * the method will return null. Only if the hand contains exactly two of a kind
	 * it will return the first of that two cards. That is, for [ACE,THREE,ACE, TEN,
	 * QUEEN] it returns the first ACE.
	 * 
	 * Example 2: If you call containsNumberOfAKind(2) for [ACE,THREE,ACE,TEN,TEN]
	 * it will return the first TEN. That is, its returning the first card of the
	 * last pair it founds.
	 * 
	 * @param number specifies the number of kind
	 * @return the first card of the last number of a kind if currentHand contains
	 *         number of a kind, otherwise null
	 */
	public Card containsNumberOfAKind(int number) {
		Card currCard;
		Card tempCard;
		for (int i = 0; i < currentHand.size() + 1 - number; i++) {
			currCard = currentHand.get(i);
			int numberOfSameKind = 1;
			for (int j = i + 1; j < currentHand.size(); j++) {
				tempCard = currentHand.get(j);
				if (currCard.getCardValue().compareTo(tempCard.getCardValue()) == 0) {
					numberOfSameKind++;
				}
			}
			if (numberOfSameKind == number) {
				return currCard;
			}
		}
		return null;
	}

	/**
	 * Checks if the currentHand contains a straight
	 * 
	 * @return true only if the hand contains a straight otherwise false
	 */
	public boolean containsStraight() {
		List<Card> sortedHand = new ArrayList<Card>(currentHand);
		Collections.sort(sortedHand,
				(card1, card2) -> card1.getCardValue().compareTo(card2.getCardValue()));
		for (int i = 0; i < sortedHand.size() - 1; i++) {
			if (sortedHand.get(i).getCardValue()
					.ordinal() != sortedHand.get(i + 1).getCardValue().ordinal() - 1) {
				return false;
			}
		}
		return true;
	}

	public boolean containsTwoPair() {
		// TODO Auto-generated method stub
		return false;
	}

}
