package de.pokerhandevaluator;

import static de.pokerhandevaluator.hand.HandRanking.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.EnumSelector;

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
	 * Determines a winner of hand1 and hand2 if HandRanking of hand1 and hand2 are
	 * different. Otherwise compareHands is invoked to determine a winner.
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String calculate() {
		String winner = "";
		if (hand1.getCurrentHandRanking().compareTo(hand2.getCurrentHandRanking()) < 0) {
			winner = "Hand 2 wins with " + hand2.getCurrentHandRanking();
		} else if (hand1.getCurrentHandRanking()
				.compareTo(hand2.getCurrentHandRanking()) > 0) {
			winner = "Hand 1 wins with " + hand1.getCurrentHandRanking();
		} else {
			winner = compareHands();
		}
		return winner;
	}

	/**
	 * Depending on the HandRanking of hand1 (and hand2) different methods are
	 * invoked to determine a winner. In case of a royal flush then the message
	 * contains that it is a split pot
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game or if it is a
	 *         split pot
	 */
	public String compareHands() {
		String winner = "";
		if (hand1.getCurrentHandRanking().compareTo(ROYAL_FLUSH) == 0) {
			winner = "Split Pot " + hand1.getCurrentHandRanking();
		}
		if (hand1.getCurrentHandRanking().compareTo(STRAIGHT_FLUSH) == 0) {
			winner = compareHighestCardInnerRanking();
		}
		if (hand1.getCurrentHandRanking().compareTo(FOUR_OF_A_KIND) == 0) {
			winner = compareThreeOrFourOfAKind(4);
		}
		if (hand1.getCurrentHandRanking().compareTo(FULL_HOUSE) == 0) {
			winner = compareFullHouse();
		}
		if (hand1.getCurrentHandRanking().compareTo(FLUSH) == 0) {
			winner = compareFlush();
		}
		if (hand1.getCurrentHandRanking().compareTo(STRAIGHT) == 0) {
			winner = compareHighestCardInnerRanking();
		}
		if (hand1.getCurrentHandRanking().compareTo(THREE_OF_A_KIND) == 0) {
			winner = compareThreeOrFourOfAKind(3);
		}
		if (hand1.getCurrentHandRanking().compareTo(TWO_PAIR) == 0) {
			winner = compareTwoPair();
		}
		if (hand1.getCurrentHandRanking().compareTo(PAIR) == 0) {
			winner = comparePair();
		}
		if (hand1.getCurrentHandRanking().compareTo(HIGH_CARD) == 0) {
			winner = compareHighCards();
		}
		return winner;

	}

	/**
	 * Compares hand1 and hand2 and calls compareHighCardsList to determine a winner
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String compareHighCards() {
		String winner = "";
		// map every card to it's card value
		List<CardValue> cardValueList1 = hand1.getCurrentHand().stream()
				.map(card -> card.getCardValue()).collect(Collectors.toList());
		List<CardValue> cardValueList2 = hand2.getCurrentHand().stream()
				.map(card -> card.getCardValue()).collect(Collectors.toList());

		winner = compareHighCardsList(cardValueList1, cardValueList2);
		return winner;
	}

	/**
	 * Compares hand1 and hand2, each with one pair. If the pair have the same value
	 * then compareHighestCardOuterRanking is called to make a decision.
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String comparePair() {
		String winner = "";
		List<Card> pairCardHand1 = hand1.containsPair();
		List<Card> pairCardHand2 = hand2.containsPair();
		if (pairCardHand1.get(0).getCardValue()
				.compareTo(pairCardHand2.get(0).getCardValue()) < 0) {
			winner = "Hand 2 wins with " + hand2.getCurrentHandRanking();
		} else if (pairCardHand1.get(0).getCardValue()
				.compareTo(pairCardHand2.get(0).getCardValue()) > 0) {
			winner = "Hand 1 wins with " + hand2.getCurrentHandRanking();
		} else {
			winner = compareHighestCardOuterRanking();
		}
		return winner;
	}

	/**
	 * Compares hand1 and hand2, each with two pair. If the pairs have the same
	 * values then compareHighestCardOuterRanking is called to make a decision.
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String compareTwoPair() {
		String winner = "";
		List<Card> twoPairCardsHand1 = hand1.containsPair();
		List<Card> twoPairCardsHand2 = hand2.containsPair();
		Collections.sort(twoPairCardsHand1,
				(card1, card2) -> card1.getCardValue().compareTo(card2.getCardValue()));
		Collections.sort(twoPairCardsHand2,
				(card1, card2) -> card1.getCardValue().compareTo(card2.getCardValue()));

		for (int i = twoPairCardsHand1.size() - 1; i >= 0; i--) {
			if (twoPairCardsHand1.get(i).getCardValue()
					.compareTo(twoPairCardsHand2.get(i).getCardValue()) == 0) {
				continue;
			}
			if (twoPairCardsHand1.get(i).getCardValue()
					.compareTo(twoPairCardsHand2.get(i).getCardValue()) < 0) {
				winner = "Hand 2 wins with " + hand2.getCurrentHandRanking();
			}
			if (twoPairCardsHand1.get(i).getCardValue()
					.compareTo(twoPairCardsHand2.get(i).getCardValue()) > 0) {
				winner = "Hand 1 wins with " + hand1.getCurrentHandRanking();
			}
		}
		// check the last card
		if (winner.equals("")) {
			winner = compareHighestCardOuterRanking();
		}
		return winner;
	}

	/**
	 * Removes all duplicate card values from List
	 * 
	 * @param cardValueList a list of card values to be cleared of duplicates
	 * @return a list of card values without duplicates
	 */
	public List<CardValue> removeDuplicates(List<CardValue> cardValueList) {
		return cardValueList.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * Maps every card  in a list to it's card value
	 * @param cardList list of cards to map
	 * @return list of card values
	 */
	public List<CardValue> mapToCardValue(List<Card> cardList) {
		return cardList.stream().map(card -> card.getCardValue())
				.collect(Collectors.toList());
	}

	/**
	 * Compares the highest card values of hand1 and hand2 which are not included in
	 * a pair, three or four of a kind, straight, full house, flush, straight flush
	 * or royal flush. This method is used by comparePair and compareTwoPair.
	 * 
	 * Note: In case of two pair it determines simply the highest card value of the
	 * last remaining card of hand1 and the last remaining card of hand2.<br />
	 * 
	 * In case of one pair it invokes compareHighCardsList()
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String compareHighestCardOuterRanking() {
		String winner = "";
		// map every card to it's card value
		List<CardValue> cardValueList1 = mapToCardValue(hand1.getCurrentHand());
		List<CardValue> cardValueList2 = mapToCardValue(hand2.getCurrentHand());
		// remove duplicates
		List<CardValue> listWithoutDuplicates1 = removeDuplicates(cardValueList1);
		List<CardValue> listWithoutDuplicates2 = removeDuplicates(cardValueList2);

		List<Card> pairCards1 = hand1.containsPair();
		List<Card> pairCards2 = hand2.containsPair();

		for (int i = 0; i < listWithoutDuplicates1.size(); i++) {
			for (int j = 0; j < pairCards1.size(); j++) {
				if (pairCards1.get(j).getCardValue()
						.compareTo(listWithoutDuplicates1.get(i)) == 0) {
					listWithoutDuplicates1.remove(i);

				}
				if (pairCards2.get(j).getCardValue()
						.compareTo(listWithoutDuplicates2.get(i)) == 0) {
					listWithoutDuplicates2.remove(i);
				}
			}
		}
		// case: 2 pairs
		if (listWithoutDuplicates1.size() == 1) {
			if (listWithoutDuplicates1.get(0)
					.compareTo(listWithoutDuplicates2.get(0)) < 0) {
				winner = "Hand 2 wins with " + hand2.getCurrentHandRanking();
			} else if (listWithoutDuplicates1.get(0)
					.compareTo(listWithoutDuplicates2.get(0)) > 0) {
				winner = "Hand 1 wins with " + hand1.getCurrentHandRanking();
			} else {
				winner = "Split Pot " + hand1.getCurrentHandRanking();
			}
		}

		// case: 1 pair
		if (listWithoutDuplicates1.size() > 1) {
			winner = compareHighCardsList(listWithoutDuplicates1, listWithoutDuplicates2);
		}
		return winner;
	}

	/**
	 * This method determines the winner by comparing a list of cards which acts as
	 * a high card.
	 * 
	 * @param listWithoutDuplicates1 list of card values of hand1 without duplicates
	 * @param listWithoutDuplicates2 list of card values of hand2 without duplicates
	 * @return a string with a message if hand1 or hand2 wins the game. If the lists
	 *         contains exactly the same card values then the message contains that
	 *         it is a split pot
	 */
	public String compareHighCardsList(List<CardValue> listWithoutDuplicates1,
			List<CardValue> listWithoutDuplicates2) {
		String winner = "";
		Collections.sort(listWithoutDuplicates1,
				(cardVal1, cardVal2) -> cardVal1.compareTo(cardVal2));
		Collections.sort(listWithoutDuplicates2,
				(cardVal1, cardVal2) -> cardVal1.compareTo(cardVal2));
		for (int i = listWithoutDuplicates1.size()-1; i >= 0; i--) {
			if (listWithoutDuplicates1.get(i)
					.compareTo(listWithoutDuplicates2.get(i)) < 0) {
				winner = "Hand 2 wins with " + hand2.getCurrentHandRanking();
				break;
			} else if (listWithoutDuplicates1.get(i)
					.compareTo(listWithoutDuplicates2.get(i)) > 0) {
				winner = "Hand 1 wins with " + hand1.getCurrentHandRanking();
				break;
			} else {
				continue;
			}
		}
		if (winner.equals("")) {
			winner = "Split Pot " + hand1.getCurrentHandRanking();
		}

		return winner;
	}

	/**
	 * Compares the flush of hand1 and hand2 and determines the winner
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String compareFlush() {
		String winner = "";
		List<Card> sortedHand1 = new ArrayList<Card>(hand1.getCurrentHand());
		List<Card> sortedHand2 = new ArrayList<Card>(hand2.getCurrentHand());
		Collections.sort(sortedHand1,
				(card1, card2) -> card1.getCardValue().compareTo(card2.getCardValue()));
		Collections.sort(sortedHand2,
				(card1, card2) -> card1.getCardValue().compareTo(card2.getCardValue()));

		for (int i = sortedHand1.size() - 1; i >= 0; i--) {
			if (sortedHand1.get(i).getCardValue()
					.compareTo(sortedHand2.get(i).getCardValue()) == 0) {
				continue;
			} else if (sortedHand1.get(i).getCardValue()
					.compareTo(sortedHand2.get(i).getCardValue()) < 0) {
				winner = "Hand 2 wins with " + hand2.getCurrentHandRanking();
			} else {
				winner = "Hand 1 wins with " + hand1.getCurrentHandRanking();
			}
		}
		return winner;
	}

	/**
	 * Compares the card value of the three of kind in a full house from hand1 and
	 * hand2
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String compareFullHouse() {
		if (hand1.containsThreeOrFourOfAKind(3).getCardValue()
				.compareTo(hand2.containsThreeOrFourOfAKind(3).getCardValue()) < 0) {
			return "Hand 2 wins with " + hand2.getCurrentHandRanking();
		} else {
			return "Hand 1 wins with " + hand1.getCurrentHandRanking();
		}
	}

	/**
	 * Compares the card value of the three or four of kind from hand1 and hand2
	 * 
	 * @param number number of a kind
	 * @return a string with a message if hand1 or hand2 wins the game
	 */
	public String compareThreeOrFourOfAKind(int number) {
		if (hand1.containsThreeOrFourOfAKind(number).getCardValue()
				.compareTo(hand2.containsThreeOrFourOfAKind(number).getCardValue()) < 0) {
			return "Hand 2 wins with " + hand2.getCurrentHandRanking();
		} else {
			return "Hand 1 wins with " + hand1.getCurrentHandRanking();
		}
	}

	/**
	 * Determines the winner of two hands where all cards of a hand are part of a
	 * hand ranking
	 * 
	 * @return a string with a message if hand1 or hand2 wins the game or whether
	 *         there is a tie
	 */
	public String compareHighestCardInnerRanking() {
		if (hand1.getHighestCard().getCardValue()
				.compareTo(hand2.getHighestCard().getCardValue()) < 0) {
			return "Hand 2 wins with " + hand2.getCurrentHandRanking();
		} else if (hand1.getHighestCard().getCardValue()
				.compareTo(hand2.getHighestCard().getCardValue()) > 0) {
			return "Hand 1 wins with " + hand1.getCurrentHandRanking();
		} else {
			return "Split Pot " + hand1.getCurrentHandRanking();
		}
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
