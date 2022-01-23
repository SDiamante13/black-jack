package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private final Player player = new Player("Lorenzo");

    @Test
    void playerCanAddACard() {
        player.addCard(Card.FIVE);

        assertThat(player.handCount()).isEqualTo(1);
    }
}