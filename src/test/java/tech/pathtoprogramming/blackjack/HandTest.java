package tech.pathtoprogramming.blackjack;


import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand = new Hand();

    @Test
    void handCount_returnsTheNumberOfCardsInTheHand() {
        hand.addCard(Card.TWO);
        hand.addCard(Card.THREE);

        int actualHandCount = hand.handCount();

        assertThat(actualHandCount).isEqualTo(2);
    }

    @Test
    void totalValue_calculatesTheHandsTotalValue() {
        hand.addCard(Card.TWO);
        hand.addCard(Card.THREE);
        TotalValue expectedTotalValue = new TotalValue(5, Integer.MAX_VALUE);

        TotalValue actualTotalValue = hand.totalValue();

        assertThat(actualTotalValue).isEqualTo(expectedTotalValue);
    }

    @Test
    void totalValue_returnsTwoSumsWhenAceIsPresent() {
        hand.addCard(Card.ACE);
        hand.addCard(Card.THREE);
        TotalValue expectedTotalValue = new TotalValue(14, 4);

        TotalValue actualTotalValue = hand.totalValue();

        assertThat(actualTotalValue).isEqualTo(expectedTotalValue);
    }

    @Test
    void totalValue_Aces2() {
        hand.addCard(Card.ACE);
        hand.addCard(Card.THREE);
        hand.addCard(Card.ACE);
        TotalValue expectedTotalValue = new TotalValue(15, 5);

        TotalValue actualTotalValue = hand.totalValue();

        assertThat(actualTotalValue).isEqualTo(expectedTotalValue);
    }

    @Test
    void totalValue_Aces3() {
        hand.addCard(Card.ACE);
        hand.addCard(Card.SIX);
        hand.addCard(Card.ACE);
        hand.addCard(Card.ACE);
        TotalValue expectedTotalValue = new TotalValue(19, 9);

        TotalValue actualTotalValue = hand.totalValue();

        assertThat(actualTotalValue).isEqualTo(expectedTotalValue);
    }

    @Test
    void isBusted_returnsFalseWhenTheTotalValueIsLessThanOrEqualTo21() {
        hand.addCard(Card.TWO);
        hand.addCard(Card.THREE);

        boolean isHandBusted = hand.isBusted();

        assertThat(isHandBusted).isFalse();
    }

    @Test
    void isBusted_returnsTrueWhenTheTotalValueIsOver21() {
        hand.addCard(Card.FIVE);
        hand.addCard(Card.QUEEN);
        hand.addCard(Card.TEN);

        boolean isHandBusted = hand.isBusted();

        assertThat(isHandBusted).isTrue();
    }

    @Test
    void isBusted_takesTheLessOfTwoValues() {
        hand.addCard(Card.TEN);
        hand.addCard(Card.FOUR);
        hand.addCard(Card.ACE);
        hand.addCard(Card.ACE);
        hand.addCard(Card.ACE);

        boolean isHandBusted = hand.isBusted();

        assertThat(isHandBusted).isFalse();
    }

    @Test
    void aPlayersHandCanBeSeenByAllPlayers() {
        hand.addCard(Card.TEN);
        hand.addCard(Card.KING);

        List<Card> cards = hand.showCards();

        assertThat(cards)
                .extracting("rank", "value")
                .contains(
                        tuple("10", 10),
                        tuple("K", 10)
                );
    }

    @Test
    void aPlayersHandCanOnlyBeModifiedByTheDealer() {
        hand.addCard(Card.TEN);
        hand.addCard(Card.KING);

        List<Card> cards = hand.showCards();

        assertThrows(UnsupportedOperationException.class,
                () -> cards.add(Card.ACE)
        );
    }
}