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

import java.util.List;

import de.pokerhandevaluator.hand.card.Card;
import de.pokerhandevaluator.hand.card.CardValue;

/**
 * Represents a hand in a poker game. 
 * @author Josef Weldemariam
 *
 */
public class Hand {
	private List<Card> currentHand;
	
	public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
		currentHand = List.of(card1, card2, card3, card4, card5);
	}

	public List<Card> getCurrentHand() {
		return currentHand;
	}

	public void setCurrentHand(List<Card> currentHand) {
		this.currentHand = currentHand;
	}
	
	/**
	 * returns the card with the highest CardValue
	 * @return
	 */
	public Card getHighestCard() {
		Card highestCard = getCurrentHand().get(0);
		Card tmpCard;
		for(int i =1; i< currentHand.size(); i++) {
			tmpCard = currentHand.get(i);
			if(tmpCard.getCardValue().compareTo(highestCard.getCardValue()) > 0) {
				highestCard = tmpCard;
			}
		}
		
		return highestCard;
	}
	
	
}
