package de.pokerhandevaluator.hand;

import static de.pokerhandevaluator.hand.HandRanking.*;
import static de.pokerhandevaluator.hand.card.CardValue.ACE;

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
	private HandRanking currentHandRanking;

	/**
	 * Creates an immutable Hand object with 5 Card objects
	 * 
	 * @param card1 card 1 of the hand
	 * @param card2 card 2 of the hand
	 * @param card3 card 3 of the hand
	 * @param card4 card 4 of the hand
	 * @param card5 card 5 of the hand
	 */
	public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
		currentHand = List.of(card1, card2, card3, card4, card5);
		setHandRanking();
	}

	/**
	 * @return the currentHand
	 */
	public List<Card> getCurrentHand() {
		return currentHand;
	}

	/**
	 * @return the currentHandRanking
	 */
	public HandRanking getCurrentHandRanking() {
		return currentHandRanking;
	}

	/**
	 * Sets the currentHandRanking
	 * 
	 * @param ranking The rating for a Hand Object
	 */
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
		if (containsPair().size() == 1 && containsThreeOrFourOfAKind(3) != null) {
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
	 * This method determines if a currentHand contains three or four of a kind
	 * depending on the passed numberOfAKind parameter. If it founds three or four
	 * of a kind it returns the first card of the find.
	 * 
	 * @param numberOfKind number can be replaced by three or four to find 3 or 4 of
	 *                     a kind
	 * @return the first card of a find if 3(for numberOfAKind = 3) or 4(for
	 *         numberOfAKind = 4) of a kind exists, otherwise null
	 */
	public Card containsThreeOrFourOfAKind(int numberOfAKind) {
		List<CardValue> cardValueList = currentHand.stream()
				.map(card -> card.getCardValue()).collect(Collectors.toList());
		// remove duplicates
		List<CardValue> listWithoutDuplicates = cardValueList.stream().distinct()
				.collect(Collectors.toList());

		for (CardValue value : listWithoutDuplicates) {
			if (cardValueList.stream()
					.filter(cardValue -> value.compareTo(cardValue) == 0)
					.collect(Collectors.toList()).size() == numberOfAKind) {

				return currentHand.stream()
						.filter(card -> card.getCardValue().compareTo(value) == 0)
						.collect(Collectors.toList()).get(0);
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

	/**
	 * This methods determines if a currentHand contains one or two pairs. If it
	 * founds one pair it adds the first of those two cards to a list. This method
	 * will only add a card if there exists exactly two cards in currentHand with
	 * the same card value. This method can be used to find a single pair or two
	 * pairs. If the size of the returned List is 2, currentHand contains two pairs.
	 * If the size is 1, currentHand contains exactly one pair.
	 * 
	 * Note: This method also finds a pair that is part of a full house
	 * 
	 * @return A List of distinct(with respect to CardValue) cards where every card
	 *         represents a pair in currentHand
	 */
	public List<Card> containsPair() {
		List<Card> pairCards = new ArrayList<Card>();
		List<CardValue> cardValueList = currentHand.stream()
				.map(card -> card.getCardValue()).collect(Collectors.toList());
		// remove duplicates
		List<CardValue> listWithoutDuplicates = cardValueList.stream().distinct()
				.collect(Collectors.toList());

		for (CardValue value : listWithoutDuplicates) {
			if (cardValueList.stream()
					.filter(cardValue -> value.compareTo(cardValue) == 0)
					.collect(Collectors.toList()).size() == 2) {

				pairCards.add(currentHand.stream()
						.filter(card -> card.getCardValue().compareTo(value) == 0)
						.collect(Collectors.toList()).get(0));
			}
		}

		return pairCards;
	}
	
	/**
	 * Sets the ranking for a t
	 * @param hand
	 */
	public void setHandRanking() {
		if(getHighestCard().getCardValue().compareTo(ACE) == 0 && containsFlush() && containsStraight()) {
			currentHandRanking = ROYAL_FLUSH;
			return;
		}
		if(containsFlush() && containsStraight()) {
			currentHandRanking = STRAIGHT_FLUSH;
			return;
		}
		if(containsThreeOrFourOfAKind(4) != null) {
			currentHandRanking = FOUR_OF_A_KIND;
			return;
		}
		if(containsFullHouse()) {
			currentHandRanking = FULL_HOUSE;
			return;
		}
		if(containsFlush()) {
			currentHandRanking = FLUSH;
			return;
		}
		if(containsStraight()) {
			currentHandRanking = STRAIGHT;
			return;
		}
		if(containsThreeOrFourOfAKind(3) != null) {
			currentHandRanking = THREE_OF_A_KIND;
			return;
		}
		if(containsPair().size() == 2) {
			currentHandRanking = TWO_PAIR;
			return;
		}
		if(containsPair().size() == 1) {
			currentHandRanking = PAIR;
			return;
		}
		currentHandRanking = HIGH_CARD;
	}

}
