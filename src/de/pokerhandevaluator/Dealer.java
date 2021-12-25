package de.pokerhandevaluator;

import static de.pokerhandevaluator.hand.card.CardValue.*;
import static de.pokerhandevaluator.hand.card.CardSuit.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;
import de.pokerhandevaluator.hand.card.CardSuit;
import de.pokerhandevaluator.hand.card.CardValue;

/**
 * Represents a dealer to deal the cards. Contains one method to create the card
 * deck and one to create the hands.
 * 
 * @author Josef Weldemariam
 *
 */
public class Dealer {
	
	/**
	 * List of all possible card values
	 */
	private final List<CardValue> values = List.of(TWO, THREE, FOUR, FIVE, SIX, SEVEN,
			EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE);
	
	/**
	 * List of all possible card suits
	 */
	private final List<CardSuit> suits = List.of(HEARTS, DIAMONDS, CLUBS, SPADES);

	/**
	 * Contains the card deck
	 */
	private final Stack<Card> deck;

	/**
	 * Contains the hands
	 */
	private final List<Hand> hands;

	/**
	 * Creates a deck and the hands
	 */
	public Dealer() {
		deck = createDeck();
		hands = createHands();
	}

	/**
	 * Creates a shuffled card deck 
	 * @return stack with shuffled cards
	 */
	private Stack<Card> createDeck() {
		Stack<Card> deck = new Stack<Card>();
		for (CardValue value : values) {
			for (CardSuit suit : suits) {
				deck.add(new Card(suit, value));
			}
		}
		Collections.shuffle(deck);
		return deck;
	}

	/**
	 * Creates the hands based on the deck.
	 * @return list of hands
	 */
	private List<Hand> createHands() {
		List<Hand> hands = new ArrayList<Hand>();
		List<Card> cardList1 = new ArrayList<Card>();
		List<Card> cardList2 = new ArrayList<Card>();
		while (cardList1.size() < 6) {
			cardList1.add(deck.pop());
			cardList2.add(deck.pop());
		}
		Hand hand1 = new Hand(cardList1.get(0), cardList1.get(1), cardList1.get(2),
				cardList1.get(3), cardList1.get(4));
		Hand hand2 = new Hand(cardList2.get(0), cardList2.get(1), cardList2.get(2),
				cardList2.get(3), cardList2.get(4));
		hands.addAll(List.of(hand1, hand2));
		return hands;
	}

	public List<Hand> getHands() {
		return hands;
	}

}
