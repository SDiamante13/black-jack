package tech.pathtoprogramming.blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GameTest {

    private Deck deck;
    private Player[] players;
    private Game game;


    @BeforeEach
    void setUp() {
        deck = new Deck();
        deck.shuffle();
        players = new Player[]{new Player("Phil"), new Player("Tracy")};
        game = new Game(deck, players);
    }

    @Test
    void dealsTwoCardsToEachPlayerOnStart() {
        game.start();

        assertThat(game.getPlayers()[0].handCount()).isEqualTo(2);
        assertThat(game.getPlayers()[1].handCount()).isEqualTo(2);
    }
}