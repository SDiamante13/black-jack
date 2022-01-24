package tech.pathtoprogramming.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DealerTest {

    @Mock
    private Deck mockDeck;

    @Mock
    private Player mockPlayer;

    Dealer dealer;

    private static final String DEALER = "DEALER";

    @BeforeEach
    void setUp() {
        dealer = new Dealer(DEALER, mockDeck);
    }

    @Test
    void dealerCanDealToThemselves() {
        given(mockDeck.drawCard())
                .willReturn(Card.FIVE);

        dealer.dealCardTo(dealer);

        assertThat(dealer.handCount()).isEqualTo(1);
    }

    @Test
    void dealerCanDealToOtherPlayers() {
        given(mockDeck.drawCard())
                .willReturn(Card.FIVE);

        dealer.dealCardTo(mockPlayer);

        then(mockPlayer)
                .should()
                .addCard(any(Card.class));
    }

    @Test
    void dealerDeals2CardsToEveryPlayerAndThemselves() {
        BDDMockito.given(mockDeck.drawCard())
                .willReturn(Card.FIVE, Card.JACK, Card.FIVE, Card.JACK);

        dealer.dealInitialCards(List.of(mockPlayer, mockPlayer));

        then(mockPlayer)
                .should(times(4))
                .addCard(any(Card.class));
        assertThat(dealer.handCount()).isEqualTo(2);
    }

    @Test
    void dealInitialCardsThrowsNoPlayersExceptionWhenNoPlayersAreInTheGame() {
        assertThrows(NoPlayersException.class,
                () -> dealer.dealInitialCards(null)
        );
    }
}