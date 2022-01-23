package tech.pathtoprogramming.blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.artsok.RepeatedIfExceptionsTest;
import org.junit.jupiter.api.Test;


class DeckTest {

    private final Deck deck = new Deck();

    @Test
    void whenYouInitializeADeckItContains52Cards() {
        int count = deck.cardCount();

        assertThat(count).isEqualTo(52);
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    void youCanShuffleADeckOfCards() {
        String initialTopCardRank = deck.lookAtTopCard().cardRank();
        deck.shuffle();

        assertThat(deck.lookAtTopCard().cardRank()).isNotEqualTo(initialTopCardRank);
    }

    @Test
    void drawingACardFromTheDeckReducesTheCardCount() {
        deck.drawCard();

        assertThat(deck.cardCount()).isEqualTo(51);
    }
}
