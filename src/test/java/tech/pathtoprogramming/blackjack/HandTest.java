package tech.pathtoprogramming.blackjack;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}