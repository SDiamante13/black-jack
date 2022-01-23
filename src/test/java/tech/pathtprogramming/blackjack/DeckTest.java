package tech.pathtprogramming.blackjack;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.blackjack.Deck;
import tech.pathtoprogramming.blackjack.NoPlayersException;
import tech.pathtoprogramming.blackjack.Player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeckTest {
    private final Deck deck = new Deck();

    // when you initialize a deck it contains 52 cards
    // you can *shuffle* a deck of cards
    // a special Player called a Dealer can *deal* cards

    @Test
    void whenYouInitializeADeckItContains52Cards() {
        int count = deck.cardCount();

        assertThat(count).isEqualTo(52);
    }

    @Test
    void youCanShuffleADeckOfCards() {
        String initialTopCardRank = deck.lookAtTopCard().cardRank();
        deck.shuffle();

        assertThat(deck.lookAtTopCard().cardRank()).isNotEqualTo(initialTopCardRank);
    }

    @Test
    void cannotDealCardsToNoPlayers() {
        assertThrows(NoPlayersException.class, deck::deal);
    }

    @Test
    void drawingACardFromTheDeckReducesTheCardCount() {
        deck.draw();

        assertThat(deck.cardCount()).isEqualTo(51);
    }

    @Test
    @Disabled
    void deal2CardsToEveryPlayer() {
        Player player1 = new Player();
        Player player2 = new Player();

        deck.deal(player1, player2);

        assertThat(player1.handCount()).isEqualTo(2);
        assertThat(player2.handCount()).isEqualTo(2);
    }
}
