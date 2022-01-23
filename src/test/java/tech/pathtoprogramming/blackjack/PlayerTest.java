package tech.pathtoprogramming.blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PlayerTest {

    private final Player player = new Player("Lorenzo");

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