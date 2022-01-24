package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class GameTest {

    private Deck mockDeck;
    private List<Player> players;
    private Dealer dealer;
    private Game game;

    @BeforeEach
    void setUp() {
        mockDeck = Mockito.mock(Deck.class);
        players = Arrays.asList(new Player("Phil"));
        dealer = new Dealer("James", mockDeck);
        game = new Game(dealer, players);
    }

    @Test
    void dealAllPlayersAndDealerTwoCards() {
        given(mockDeck.drawCard())
                        .willReturn(Card.TEN);
        given(mockDeck.drawCard())
                        .willReturn(Card.TEN);
        given(mockDeck.drawCard())
                .willReturn(Card.FIVE);
        given(mockDeck.drawCard())
                .willReturn(Card.THREE);

        game.start();

        assertThat(game.getPlayers().get(0).handCount()).isEqualTo(2);
        assertThat(game.getDealer().handCount()).isEqualTo(2);
    }
}