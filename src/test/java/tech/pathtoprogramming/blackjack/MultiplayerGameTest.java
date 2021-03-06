package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static tech.pathtoprogramming.blackjack.ActionType.HIT;
import static tech.pathtoprogramming.blackjack.ActionType.STAY;

@ExtendWith(MockitoExtension.class)
class MultiplayerGameTest {

    @Mock
    private Deck mockDeck;

    @Mock
    private ActionInput mockActionInputPlayer1;

    @Mock
    private ActionInput mockActionInputPlayer2;

    private Game game;

    private static final String DEALER = "DEALER";
    public static final String PLAYER_1 = "PLAYER_1";
    public static final String PLAYER_2 = "PLAYER_2";
    public static final List<Card> BUSTED_HAND = List.of(
            Card.TEN, Card.FIVE, Card.SEVEN
    );

    @BeforeEach
    void setUp() {
        game = new Game(
                new Dealer(DEALER, mockDeck),
                List.of(
                        new Player(PLAYER_1, new Hand(), mockActionInputPlayer1),
                        new Player(PLAYER_2, new Hand(), mockActionInputPlayer2)
                )
        );
    }

    @Test
    void allPlayersBust_dealerWins_withoutNeedingToHit() {
        playerChoosesTo(mockActionInputPlayer1, HIT);
        playerChoosesTo(mockActionInputPlayer2, HIT);
        List<Card> player1Cards = BUSTED_HAND;
        List<Card> player2Cards = BUSTED_HAND;
        List<Card> dealerCards = List.of(Card.TEN, Card.THREE);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        player2Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        player2Cards.get(1),
                        dealerCards.get(1),
                        player1Cards.get(2),
                        player2Cards.get(2)
                );

        List<Player> winners = game.play();

        thenDealerWins(winners);
    }

    @Test
    void multiplePlayersBeatTheDealer() {
        playerChoosesTo(mockActionInputPlayer1, STAY);
        playerChoosesTo(mockActionInputPlayer2, STAY);
        List<Card> player1Cards = List.of(Card.QUEEN, Card.KING);
        List<Card> player2Cards = List.of(Card.QUEEN, Card.KING);
        List<Card> dealerCards = List.of(Card.TEN, Card.FIVE, Card.NINE);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        player2Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        player2Cards.get(1),
                        dealerCards.get(1),
                        dealerCards.get(2)
                );

        List<Player> winners = game.play();

        assertThat(winners).extracting("name")
                .contains(PLAYER_1, PLAYER_2);
    }

    @Test
    void multiplePlayersLoseToTheDealer() {
        playerChoosesTo(mockActionInputPlayer1, STAY);
        playerChoosesTo(mockActionInputPlayer2, STAY);
        List<Card> player1Cards = List.of(Card.QUEEN, Card.FIVE);
        List<Card> player2Cards = List.of(Card.QUEEN, Card.FIVE);
        List<Card> dealerCards = List.of(Card.TEN, Card.TWO, Card.NINE);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        player2Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        player2Cards.get(1),
                        dealerCards.get(1),
                        dealerCards.get(2)
                );

        List<Player> winners = game.play();

        thenDealerWins(winners);
    }

    @Test
    void onePlayerWinsWhenDealerAndOtherPlayerBusts() {
        playerChoosesTo(mockActionInputPlayer1, HIT);
        playerChoosesTo(mockActionInputPlayer2, STAY);
        List<Card> player1Cards = List.of(Card.QUEEN, Card.FIVE, Card.TEN);
        List<Card> player2Cards = List.of(Card.QUEEN, Card.FIVE);
        List<Card> dealerCards = List.of(Card.TEN, Card.FIVE, Card.QUEEN);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        player2Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        player2Cards.get(1),
                        dealerCards.get(1),
                        player1Cards.get(2),
                        dealerCards.get(2)
                );

        List<Player> winners = game.play();

        assertThat(winners).extracting("name")
                .contains(PLAYER_2);
    }

    private void playerChoosesTo(ActionInput actionInput, ActionType actionType) {
        given(actionInput.nextActionType()).willReturn(actionType);
    }

    private void thenDealerWins(List<Player> winners) {
        assertThat(winners).isEmpty();
    }
}