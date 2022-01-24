package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class DealerTest {

    private Deck mockDeck;

    @BeforeEach
    void setUp() {
        mockDeck = Mockito.mock(Deck.class);
    }

    @Test
    void dealerCanDealToThemselves() {
        given(mockDeck.drawCard())
                .willReturn(Card.FIVE);

        Dealer dealer = new Dealer("James", mockDeck);
        dealer.dealCardTo(dealer);

        assertThat(dealer.handCount()).isEqualTo(1);
    }
}