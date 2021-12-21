package de.pokerhandevaluator.hand;

import static de.pokerhandevaluator.hand.card.CardValue.ACE;
import static de.pokerhandevaluator.hand.card.CardValue.EIGHT;
import static de.pokerhandevaluator.hand.card.CardValue.FIVE;
import static de.pokerhandevaluator.hand.card.CardValue.FOUR;
import static de.pokerhandevaluator.hand.card.CardValue.JACK;
import static de.pokerhandevaluator.hand.card.CardValue.KING;
import static de.pokerhandevaluator.hand.card.CardValue.NINE;
import static de.pokerhandevaluator.hand.card.CardValue.QUEEN;
import static de.pokerhandevaluator.hand.card.CardValue.SEVEN;
import static de.pokerhandevaluator.hand.card.CardValue.SIX;
import static de.pokerhandevaluator.hand.card.CardValue.TEN;
import static de.pokerhandevaluator.hand.card.CardValue.THREE;
import static de.pokerhandevaluator.hand.card.CardValue.TWO;
import static de.pokerhandevaluator.hand.card.CardSuit.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import de.pokerhandevaluator.hand.card.Card;
import de.pokerhandevaluator.hand.card.CardValue;

/**
 * Represents a hand in a poker game.
 * 
 * @author Josef Weldemariam
 *
 */
public class Hand {
	private final List<Card> currentHand;

	public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
		currentHand = List.of(card1, card2, card3, card4, card5);
	}

	public List<Card> getCurrentHand() {
		return currentHand;
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
	 * Checks if the currentHand contains a flush returns true if currentHand
	 * contains a flush otherwise false
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
	 * Checks if the currentHand contains a street
	 * 
	 * @return true only if the hand contains a street otherwise false
	 */
	public boolean containsStreet() {
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

}
