package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    @Mock
    private ActionInput mockActionInput;

    @Spy
    private Hand spyHand;

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Lorenzo", spyHand, mockActionInput);
    }

    @Test
    void playerCanAddACard() {
        player.addCard(Card.FIVE);

        assertThat(player.handCount()).isEqualTo(1);
    }

    @Test
    void playerHasTotalHandValue() {
        player.addCard(Card.SIX);
        player.addCard(Card.SIX);

        assertThat(player.totalHandValue()).isEqualTo(12);
    }

    @Test
    void aPlayersCardsCanBeSeenByEveryoneInTheGame() {
        player.addCard(Card.SIX);
        player.addCard(Card.FIVE);
        player.addCard(Card.JACK);

        String cards = player.showCards();

        assertThat(cards).isEqualTo("6,5,J");
    }
}