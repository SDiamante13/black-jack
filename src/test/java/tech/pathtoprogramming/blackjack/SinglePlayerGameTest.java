package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static tech.pathtoprogramming.blackjack.ActionType.HIT;
import static tech.pathtoprogramming.blackjack.ActionType.STAY;

@ExtendWith(MockitoExtension.class)
class SinglePlayerGameTest {

    @Mock
    private Deck mockDeck;

    @Mock
    private ActionInput mockActionInput;

    private Game game;

    private static final String DEALER = "DEALER";
    public static final String PLAYER_1 = "PLAYER_1";
    public static final List<Card> BUSTED_HAND = List.of(
            Card.TEN, Card.FIVE, Card.SEVEN
    );

    @BeforeEach
    void setUp() {
        game = new Game(
                new Dealer(DEALER, mockDeck),
                List.of(new Player(PLAYER_1, mockActionInput))
        );
    }

    @Test
    void player1Busts_dealerWins_withoutNeedingToHit() {
        playerChoosesTo(mockActionInput, HIT);
        List<Card> player1Cards = BUSTED_HAND;
        List<Card> dealerCards = List.of(Card.TEN, Card.THREE);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        dealerCards.get(1),
                        player1Cards.get(2)
                );

        List<Player> winners = game.play();

        thenDealerWins(winners);
        then(mockDeck)
                .should(times(5))
                .drawCard();
    }

    @Test
    void player1ChoosesToStay_dealerBusts_player1Wins() {
        playerChoosesTo(mockActionInput, STAY);
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

        List<Player> winners = game.play();

        assertThat(winners).extracting("name")
                .contains(PLAYER_1);
    }

    @Test
    void player1DoesNotBust_dealerHasHigherTotal_dealerWins() {
        playerChoosesTo(mockActionInput, STAY);
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

        List<Player> winners = game.play();

        thenDealerWins(winners);
    }

    @Test
    void allPlayersBust_dealerDoesNotHit_dealerWins() {
        playerChoosesTo(mockActionInput, STAY);
        List<Card> player1Cards = List.of(Card.TEN, Card.FIVE, Card.KING);
        List<Card> dealerCards = List.of(Card.TEN, Card.THREE);
        given(mockDeck.drawCard())
                .willReturn(
                        player1Cards.get(0),
                        dealerCards.get(0),
                        player1Cards.get(1),
                        dealerCards.get(1)
                );

        List<Player> winners = game.play();

        thenDealerWins(winners);
    }

    private void thenDealerWins(List<Player> winners) {
        assertThat(winners).isEmpty();
    }

    private void playerChoosesTo(ActionInput actionInput, ActionType actionType) {
        given(actionInput.nextActionType()).willReturn(actionType);
    }
}