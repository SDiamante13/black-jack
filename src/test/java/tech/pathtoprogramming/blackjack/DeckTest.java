package tech.pathtoprogramming.blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import tech.pathtoprogramming.blackjack.Deck;
import tech.pathtoprogramming.blackjack.NoPlayersException;
import tech.pathtoprogramming.blackjack.Player;

class DeckTest {

    private final Deck deck = new Deck();

    @Test
    void whenYouInitializeADeckItContains52Cards() {
        int count = deck.cardCount();

        assertThat(count).isEqualTo(52);
    }

    @RepeatedTest(3)
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
        deck.drawCard();

        assertThat(deck.cardCount()).isEqualTo(51);
    }

    @Test
    void deal2CardsToEveryPlayer() {
        Player player1 = new Player("Lois");
        Player player2 = new Player("Clark");

        deck.deal(player1, player2);

        assertThat(player1.handCount()).isEqualTo(2);
        assertThat(player2.handCount()).isEqualTo(2);
    }
}
