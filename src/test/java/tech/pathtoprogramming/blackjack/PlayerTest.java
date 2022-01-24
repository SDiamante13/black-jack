package tech.pathtoprogramming.blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    @Mock
    private ActionInput mockActionInput;

    private final Player player = new Player("Lorenzo", mockActionInput);

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
}