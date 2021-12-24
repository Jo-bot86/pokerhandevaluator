package test;

import static de.pokerhandevaluator.hand.card.CardSuit.*;
import static de.pokerhandevaluator.hand.card.CardValue.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.pokerhandevaluator.Calculator;
import de.pokerhandevaluator.hand.Hand;
import de.pokerhandevaluator.hand.card.Card;
import de.pokerhandevaluator.hand.card.CardValue;

public class CalculatorTest {
	Hand hand1;
	Hand hand2;
	Calculator calculator;

//	@Test
//	public void calculateTest_hand1Flush_hand2RoyalFlush() {
//		hand1 = new Hand(new Card(HEARTS, KING), new Card(HEARTS, SEVEN),
//				new Card(HEARTS, FIVE), new Card(HEARTS, TWO), new Card(HEARTS, ACE));
//		hand2 = new Hand(new Card(SPADES, JACK), new Card(SPADES, ACE),
//				new Card(SPADES, QUEEN), new Card(SPADES, TEN), new Card(SPADES, KING));
//		calculator = new Calculator(hand1, hand2);
//		System.out.println(calculator.calculate());
//	}
//
//	@Test
//	public void calculateTest_hand1TwoPairs_hand2HighCard() {
//		hand1 = new Hand(new Card(DIAMONDS, ACE), new Card(HEARTS, TWO),
//				new Card(HEARTS, KING), new Card(DIAMONDS, KING), new Card(SPADES, ACE));
//		hand2 = new Hand(new Card(SPADES, FOUR), new Card(SPADES, TWO),
//				new Card(DIAMONDS, THREE), new Card(DIAMONDS, TEN),
//				new Card(SPADES, NINE));
//		calculator = new Calculator(hand1, hand2);
//		System.out.println(calculator.calculate());
//	}

// *********************************************************************************************

	@Test
	public void compareHighCardsListTest_hand1HighCardTen_hand2_HighCardJack() {
		hand1 = new Hand(new Card(HEARTS, TWO), new Card(SPADES, FOUR),
				new Card(CLUBS, TEN), new Card(HEARTS, FIVE), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(SPADES, JACK), new Card(SPADES, TWO),
				new Card(SPADES, THREE), new Card(DIAMONDS, EIGHT),
				new Card(SPADES, NINE));
		calculator = new Calculator(hand1, hand2);

		List<CardValue> cardValueshand1 = calculator
				.mapToCardValue(hand1.getCurrentHand());
		List<CardValue> cardValueshand2 = calculator
				.mapToCardValue(hand2.getCurrentHand());

		List<CardValue> withoutDuplicates1 = calculator.removeDuplicates(cardValueshand1);
		List<CardValue> withoutDuplicates2 = calculator.removeDuplicates(cardValueshand2);

		assertEquals("Hand 2 wins with HIGH_CARD",
				calculator.compareHighCardsList(withoutDuplicates1, withoutDuplicates2));
	}

	@Test
	public void compareHighCardsListTest_hand1HighCardKing_hand2_HighCardQueen() {
		hand1 = new Hand(new Card(HEARTS, JACK), new Card(SPADES, FOUR),
				new Card(CLUBS, TEN), new Card(HEARTS, KING), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(SPADES, JACK), new Card(SPADES, QUEEN),
				new Card(SPADES, THREE), new Card(DIAMONDS, EIGHT),
				new Card(SPADES, NINE));
		calculator = new Calculator(hand1, hand2);

		List<CardValue> cardValueshand1 = calculator
				.mapToCardValue(hand1.getCurrentHand());
		List<CardValue> cardValueshand2 = calculator
				.mapToCardValue(hand2.getCurrentHand());

		List<CardValue> withoutDuplicates1 = calculator.removeDuplicates(cardValueshand1);
		List<CardValue> withoutDuplicates2 = calculator.removeDuplicates(cardValueshand2);

		assertEquals("Hand 1 wins with HIGH_CARD",
				calculator.compareHighCardsList(withoutDuplicates1, withoutDuplicates2));

	}

	@Test
	public void compareHighCardsListTest_SplitPot() {
		hand1 = new Hand(new Card(HEARTS, JACK), new Card(SPADES, FOUR),
				new Card(CLUBS, TEN), new Card(HEARTS, KING), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(SPADES, JACK), new Card(HEARTS, FOUR),
				new Card(SPADES, TEN), new Card(DIAMONDS, KING), new Card(SPADES, SIX));
		calculator = new Calculator(hand1, hand2);

		List<CardValue> cardValueshand1 = calculator
				.mapToCardValue(hand1.getCurrentHand());
		List<CardValue> cardValueshand2 = calculator
				.mapToCardValue(hand2.getCurrentHand());

		List<CardValue> withoutDuplicates1 = calculator.removeDuplicates(cardValueshand1);
		List<CardValue> withoutDuplicates2 = calculator.removeDuplicates(cardValueshand2);

		assertEquals("Split Pot",
				calculator.compareHighCardsList(withoutDuplicates1, withoutDuplicates2));

	}

//	**************************************************************************************

	@Test
	public void compareHighestCardOuterRankingTest_TwoPairAndHighCardHand2() {
		hand1 = new Hand(new Card(HEARTS, JACK), new Card(SPADES, FOUR),
				new Card(CLUBS, JACK), new Card(HEARTS, FOUR), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(CLUBS, JACK), new Card(DIAMONDS, FOUR),
				new Card(SPADES, JACK), new Card(CLUBS, FOUR), new Card(SPADES, TEN));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with TWO_PAIR",
				calculator.compareHighestCardOuterRanking());
	}

	@Test
	public void compareHighestCardOuterRankingTest_TwoPairAndHighCardHand1() {
		hand1 = new Hand(new Card(HEARTS, TWO), new Card(SPADES, FOUR),
				new Card(CLUBS, TWO), new Card(HEARTS, FOUR), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, FOUR),
				new Card(SPADES, TWO), new Card(CLUBS, FOUR), new Card(SPADES, TEN));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with TWO_PAIR",
				calculator.compareHighestCardOuterRanking());
	}

	@Test
	public void compareHighestCardOuterRankingTest_TwoPairAndHighCardSplitPot() {
		hand1 = new Hand(new Card(HEARTS, TWO), new Card(SPADES, SEVEN),
				new Card(CLUBS, TWO), new Card(HEARTS, SEVEN), new Card(HEARTS, ACE));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, TWO), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Split Pot", calculator.compareHighestCardOuterRanking());
	}

//	**************************************************************************************

	@Test
	public void comparePairTest_hand1Six_hand2Seven() {
		hand1 = new Hand(new Card(HEARTS, ACE), new Card(SPADES, SIX),
				new Card(CLUBS, KING), new Card(HEARTS, SIX), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, QUEEN), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_hand1Jack_hand2Seven() {
		hand1 = new Hand(new Card(HEARTS, ACE), new Card(SPADES, JACK),
				new Card(CLUBS, KING), new Card(HEARTS, SIX), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, QUEEN), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_samePair_hand1HighCardTen_hand2HighCardKing() {
		hand1 = new Hand(new Card(HEARTS, TWO), new Card(SPADES, SEVEN),
				new Card(CLUBS, EIGHT), new Card(HEARTS, TEN), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, QUEEN), new Card(CLUBS, SEVEN), new Card(SPADES, KING));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_samePair_hand1HighCardAce_hand2HighCardKing() {
		hand1 = new Hand(new Card(HEARTS, TWO), new Card(SPADES, SEVEN),
				new Card(CLUBS, EIGHT), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, QUEEN), new Card(CLUBS, SEVEN), new Card(SPADES, KING));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_samePair_hand1HighCardAceKing_hand2HighCardAceJack() {
		hand1 = new Hand(new Card(HEARTS, KING), new Card(SPADES, SEVEN),
				new Card(CLUBS, EIGHT), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, JACK), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_samePair_hand1HighCardAceThree_hand2HighCardAceTen() {
		hand1 = new Hand(new Card(HEARTS, THREE), new Card(SPADES, SEVEN),
				new Card(HEARTS, TWO), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, TEN), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_samePair_hand1HighCardAceKingFour_hand2HighCardAceKingJack() {
		hand1 = new Hand(new Card(HEARTS, KING), new Card(SPADES, SEVEN),
				new Card(HEARTS, FOUR), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, JACK), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, KING), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_samePair_hand1HighCardAceKingQueen_hand2HighCardAceKingJack() {
		hand1 = new Hand(new Card(HEARTS, KING), new Card(SPADES, SEVEN),
				new Card(HEARTS, QUEEN), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, JACK), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, KING), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with PAIR", calculator.comparePair());
	}

	@Test
	public void comparePairTest_samePair_sameHighCards() {
		hand1 = new Hand(new Card(HEARTS, KING), new Card(SPADES, SEVEN),
				new Card(HEARTS, QUEEN), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, QUEEN), new Card(DIAMONDS, SEVEN),
				new Card(SPADES, KING), new Card(CLUBS, SEVEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Split Pot", calculator.comparePair());
	}

//	***********************************************************************************************

	@Test
	public void compareTwoPairTest_hand1KingAce_hand2TenQueen() {
		hand1 = new Hand(new Card(HEARTS, ACE), new Card(SPADES, KING),
				new Card(SPADES, ACE), new Card(HEARTS, KING), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, QUEEN), new Card(DIAMONDS, TEN),
				new Card(SPADES, QUEEN), new Card(CLUBS, TEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_hand1NineJack_hand2KingQueen() {
		hand1 = new Hand(new Card(HEARTS, NINE), new Card(SPADES, JACK),
				new Card(SPADES, NINE), new Card(HEARTS, JACK), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, QUEEN), new Card(DIAMONDS, KING),
				new Card(SPADES, QUEEN), new Card(CLUBS, KING), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_hand1NineJack_hand2NineQueen() {
		hand1 = new Hand(new Card(HEARTS, NINE), new Card(SPADES, JACK),
				new Card(SPADES, NINE), new Card(HEARTS, JACK), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, NINE), new Card(DIAMONDS, QUEEN),
				new Card(DIAMONDS, NINE), new Card(CLUBS, QUEEN), new Card(SPADES, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_hand1NineAce_hand2NineQueen() {
		hand1 = new Hand(new Card(HEARTS, NINE), new Card(SPADES, ACE),
				new Card(SPADES, NINE), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, NINE), new Card(DIAMONDS, QUEEN),
				new Card(DIAMONDS, NINE), new Card(CLUBS, QUEEN),
				new Card(DIAMONDS, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_hand1NineTen_hand2NineQueen() {
		hand1 = new Hand(new Card(HEARTS, NINE), new Card(SPADES, TEN),
				new Card(SPADES, NINE), new Card(HEARTS, TEN), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, NINE), new Card(DIAMONDS, QUEEN),
				new Card(DIAMONDS, NINE), new Card(CLUBS, QUEEN),
				new Card(DIAMONDS, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_hand1NineAce_hand2TenAce() {
		hand1 = new Hand(new Card(HEARTS, NINE), new Card(SPADES, ACE),
				new Card(SPADES, NINE), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, TEN), new Card(DIAMONDS, QUEEN),
				new Card(DIAMONDS, TEN), new Card(CLUBS, ACE), new Card(DIAMONDS, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_hand1JackAce_hand2NineAce() {
		hand1 = new Hand(new Card(HEARTS, JACK), new Card(SPADES, ACE),
				new Card(SPADES, JACK), new Card(HEARTS, ACE), new Card(HEARTS, SEVEN));
		hand2 = new Hand(new Card(CLUBS, NINE), new Card(DIAMONDS, QUEEN),
				new Card(DIAMONDS, NINE), new Card(CLUBS, ACE), new Card(DIAMONDS, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_sameTwoPair_HighCard_hand1King_hand2Four() {
		hand1 = new Hand(new Card(HEARTS, NINE), new Card(SPADES, ACE),
				new Card(SPADES, NINE), new Card(HEARTS, ACE), new Card(HEARTS, KING));
		hand2 = new Hand(new Card(CLUBS, NINE), new Card(DIAMONDS, FOUR),
				new Card(DIAMONDS, NINE), new Card(CLUBS, ACE), new Card(DIAMONDS, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_sameTwoPair_HighCard_hand1Jack_hand2Queen() {
		hand1 = new Hand(new Card(HEARTS, FOUR), new Card(SPADES, TEN),
				new Card(SPADES, FOUR), new Card(HEARTS, TEN), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, FOUR), new Card(DIAMONDS, QUEEN),
				new Card(DIAMONDS, FOUR), new Card(CLUBS, TEN), new Card(DIAMONDS, TEN));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with TWO_PAIR", calculator.compareTwoPair());
	}

	@Test
	public void compareTwoPairTest_sameTwoPair_sameHighCard_SplitPot() {
		hand1 = new Hand(new Card(HEARTS, FOUR), new Card(SPADES, TEN),
				new Card(SPADES, FOUR), new Card(HEARTS, QUEEN), new Card(HEARTS, TEN));
		hand2 = new Hand(new Card(CLUBS, FOUR), new Card(DIAMONDS, QUEEN),
				new Card(DIAMONDS, FOUR), new Card(CLUBS, TEN), new Card(DIAMONDS, TEN));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Split Pot", calculator.compareTwoPair());
	}

//	****************************************************************************************
	// case three of a kind
	@Test
	public void compareThreeOrFourOfAKind_hand1Ten_hand2Four() {
		hand1 = new Hand(new Card(HEARTS, TEN), new Card(SPADES, TEN),
				new Card(SPADES, FOUR), new Card(DIAMONDS, TEN), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, FOUR), new Card(HEARTS, FOUR),
				new Card(DIAMONDS, FOUR), new Card(CLUBS, ACE), new Card(DIAMONDS, KING));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with THREE_OF_A_KIND",
				calculator.compareThreeOrFourOfAKind(3));
	}

	@Test
	public void compareThreeOrFourOfAKind_hand1Jack_hand2King() {
		hand1 = new Hand(new Card(HEARTS, THREE), new Card(SPADES, JACK),
				new Card(SPADES, FOUR), new Card(DIAMONDS, JACK), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, THREE), new Card(HEARTS, KING),
				new Card(DIAMONDS, ACE), new Card(CLUBS, KING), new Card(DIAMONDS, KING));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with THREE_OF_A_KIND",
				calculator.compareThreeOrFourOfAKind(3));
	}

//	**********************************************************************************************

	@Test
	public void compareThreeOrFourOfAKind_hand1Two_hand2Queen() {
		hand1 = new Hand(new Card(HEARTS, TWO), new Card(SPADES, TWO),
				new Card(CLUBS, TWO), new Card(DIAMONDS, TWO), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, QUEEN), new Card(HEARTS, QUEEN),
				new Card(DIAMONDS, FOUR), new Card(SPADES, QUEEN),
				new Card(DIAMONDS, QUEEN));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with FOUR_OF_A_KIND",
				calculator.compareThreeOrFourOfAKind(4));
	}

	@Test
	public void compareThreeOrFourOfAKind_hand1Ten_hand2Nine() {
		hand1 = new Hand(new Card(HEARTS, THREE), new Card(SPADES, TEN),
				new Card(CLUBS, TEN), new Card(DIAMONDS, TEN), new Card(HEARTS, TEN));
		hand2 = new Hand(new Card(CLUBS, NINE), new Card(HEARTS, NINE),
				new Card(DIAMONDS, NINE), new Card(SPADES, NINE),
				new Card(DIAMONDS, ACE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with FOUR_OF_A_KIND",
				calculator.compareThreeOrFourOfAKind(4));
	}

//	***********************************************************************************************
//	case straight

	@Test
	public void compareHighestCardInnerRanking_Straight_hand1King_hand2Eight() {
		hand1 = new Hand(new Card(HEARTS, QUEEN), new Card(SPADES, NINE),
				new Card(CLUBS, TEN), new Card(DIAMONDS, KING), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, EIGHT), new Card(HEARTS, FIVE),
				new Card(DIAMONDS, FOUR), new Card(SPADES, SEVEN),
				new Card(DIAMONDS, SIX));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with STRAIGHT",
				calculator.compareHighestCardInnerRanking());
	}

	@Test
	public void compareHighestCardInnerRanking_Straight_hand1King_hand2Ace() {
		hand1 = new Hand(new Card(HEARTS, QUEEN), new Card(SPADES, NINE),
				new Card(CLUBS, TEN), new Card(DIAMONDS, KING), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(CLUBS, QUEEN), new Card(HEARTS, KING),
				new Card(DIAMONDS, JACK), new Card(SPADES, ACE), new Card(DIAMONDS, TEN));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with STRAIGHT",
				calculator.compareHighestCardInnerRanking());
	}

	@Test
	public void compareHighestCardInnerRanking_sameStraightSix() {
		hand1 = new Hand(new Card(HEARTS, FIVE), new Card(SPADES, THREE),
				new Card(CLUBS, FOUR), new Card(DIAMONDS, TWO), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(CLUBS, TWO), new Card(SPADES, SIX),
				new Card(DIAMONDS, THREE), new Card(SPADES, FOUR),
				new Card(DIAMONDS, FIVE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Split Pot", calculator.compareHighestCardInnerRanking());
	}

//	**********************************************************************************************
//	case straight flush

	@Test
	public void compareHighestCardInnerRanking_StraightFlush_hand1KingHeart_hand2EightSpades() {
		hand1 = new Hand(new Card(HEARTS, QUEEN), new Card(HEARTS, NINE),
				new Card(HEARTS, TEN), new Card(HEARTS, KING), new Card(HEARTS, JACK));
		hand2 = new Hand(new Card(SPADES, EIGHT), new Card(SPADES, FIVE),
				new Card(SPADES, FOUR), new Card(SPADES, SEVEN), new Card(SPADES, SIX));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 1 wins with STRAIGHT_FLUSH",
				calculator.compareHighestCardInnerRanking());
	}

	@Test
	public void compareHighestCardInnerRanking_Straight_hand1QueenDiamonds_hand2KingClubs() {
		hand1 = new Hand(new Card(DIAMONDS, QUEEN), new Card(DIAMONDS, NINE),
				new Card(DIAMONDS, TEN), new Card(DIAMONDS, EIGHT),
				new Card(DIAMONDS, JACK));
		hand2 = new Hand(new Card(CLUBS, QUEEN), new Card(CLUBS, KING),
				new Card(CLUBS, JACK), new Card(CLUBS, NINE), new Card(CLUBS, TEN));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Hand 2 wins with STRAIGHT_FLUSH",
				calculator.compareHighestCardInnerRanking());
	}

	@Test
	public void compareHighestCardInnerRanking_sameStraightFlush_Nine() {
		hand1 = new Hand(new Card(HEARTS, EIGHT), new Card(HEARTS, FIVE),
				new Card(HEARTS, FOUR), new Card(HEARTS, SEVEN), new Card(HEARTS, SIX));
		hand2 = new Hand(new Card(CLUBS, FOUR), new Card(CLUBS, SIX),
				new Card(CLUBS, SEVEN), new Card(CLUBS, EIGHT), new Card(CLUBS, FIVE));
		calculator = new Calculator(hand1, hand2);
		assertEquals("Split Pot", calculator.compareHighestCardInnerRanking());
	}
	
//	******************************************************************************************
}
