package de.pokerhandevaluator.hand.card;

/**
 * Represents a card in a poker game. This card is specified by the value and the suit.
 * The values range between 2 and 10 plus jack, queen, king and ace. 
 * The suit can be clubs, diamonds, hearts or spades
 * @author Josef Weldemariam
 *
 */
public class Card {

	/**
	 * contains the suit of the card
	 */
	private CardSuit cardSuit;
	private CardValue cardValue;
	
	/**
	 * Constructor for creating a card object
	 * @param cardSuit
	 * @param cardValue
	 */
	public Card(CardSuit cardSuit, CardValue cardValue) {
		super();
		this.cardSuit = cardSuit;
		this.cardValue = cardValue;
	}

	public CardSuit getCardSuit() {
		return cardSuit;
	}

	public void setCardSuit(CardSuit cardSuit) {
		this.cardSuit = cardSuit;
	}

	public CardValue getCardValue() {
		return cardValue;
	}

	public void setCardValue(CardValue cardValue) {
		this.cardValue = cardValue;
	}

	@Override
	public String toString() {
		return "Card [cardSuit=" + cardSuit + ", cardValue=" + cardValue + "]";
	}
	
	
	
	
	
	
}
