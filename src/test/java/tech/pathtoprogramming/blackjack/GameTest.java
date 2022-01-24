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
import static org.mockito.BDDMockito.willReturn;
import static tech.pathtoprogramming.blackjack.ActionType.HIT;

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Mock
    private Deck mockDeck;
    private List<Player> players;
    private Player player1;
    private Dealer dealer;
    private Game game;

    private static final String DEALER = "DEALER";
    public static final String PLAYER_1 = "PLAYER_1";

    @BeforeEach
    void setUp() {
        player1 = Mockito.spy(new Player(PLAYER_1));
        players = List.of(player1);
        dealer = new Dealer(DEALER, mockDeck);
        game = new Game(dealer, players);
    }

    @Test
    void player1Hits_newHandValueOf22_dealerWins() {
        playerChoosesTo(player1, HIT);

        List<Card> player1Cards = List.of(Card.TEN, Card.FIVE, Card.SEVEN);
        List<Card> dealerCards = List.of(Card.TEN, Card.THREE);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        dealerCards.get(1),
                        player1Cards.get(2)
                        );

        game.start();

        assertThat(game.winner()).isEqualTo(DEALER);
    }

    @Test
    void player1ChoosesToStay_dealerBusts_player1Wins() {
        playerChoosesTo(player1, ActionType.STAY);
        List<Card> player1Cards = List.of(Card.TEN, Card.FIVE);
        List<Card> dealerCards = List.of(Card.TEN, Card.THREE, Card.KING);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        dealerCards.get(1),
                        dealerCards.get(2)
                );

        game.start();

        assertThat(game.winner()).isEqualTo(PLAYER_1);
    }

    @Test
    void player1DoesNotBust_dealerHasHigherTotal_dealerWins() {
        playerChoosesTo(player1, ActionType.STAY);
        List<Card> player1Cards = List.of(Card.TEN, Card.FIVE);
        List<Card> dealerCards = List.of(Card.TEN, Card.THREE, Card.SEVEN);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        dealerCards.get(1),
                        dealerCards.get(2)
                );

        game.start();

        assertThat(game.winner()).isEqualTo(DEALER);
    }

    private void playerChoosesTo(Player player, ActionType hit) {
        willReturn(hit).given(player).getPlayerActionType();
    }
}