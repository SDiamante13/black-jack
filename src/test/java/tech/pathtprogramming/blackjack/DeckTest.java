package tech.pathtprogramming.blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.blackjack.Deck;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    private final Deck deck = new Deck();

    // when you initialize a deck it contains 52 cards
    // you can *shuffle* a deck of cards
    // a special Player called a Dealer can *deal* cards

    @Test
    void whenYouInitializeADeckItContains52Cards() {
        int count = deck.count();

        assertThat(count).isEqualTo(52);
    }
}
