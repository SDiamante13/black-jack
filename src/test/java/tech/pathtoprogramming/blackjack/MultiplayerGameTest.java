package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
                        new Player(PLAYER_1, mockActionInputPlayer1),
                        new Player(PLAYER_2, mockActionInputPlayer2)
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

        String winner = game.play();

        assertThat(winner).isEqualTo(DEALER);
    }


    private void playerChoosesTo(ActionInput actionInput, ActionType actionType) {
        given(actionInput.nextActionType()).willReturn(actionType);
    }
}