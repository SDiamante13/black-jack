package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Mock
    private Deck mockDeck;
    private List<Player> players;
    private Dealer dealer;
    private Game game;

    @BeforeEach
    void setUp() {
        players = List.of(new Player("Phil"));
        dealer = new Dealer("James", mockDeck);
        game = new Game(dealer, players);
    }

    @Test
    void dealAllPlayersAndDealerTwoCards() {
        given(mockDeck.drawCard())
                .willReturn(Card.TEN, Card.TEN, Card.FIVE, Card.THREE);

        game.start();

        assertThat(game.getPlayers().get(0).handCount()).isEqualTo(2);
        assertThat(game.getDealer().handCount()).isEqualTo(2);
    }
}