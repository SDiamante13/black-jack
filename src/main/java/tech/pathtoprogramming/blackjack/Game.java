package tech.pathtoprogramming.blackjack;

import java.util.Collections;
import java.util.List;

import static tech.pathtoprogramming.blackjack.ActionType.HIT;
import static tech.pathtoprogramming.blackjack.ActionType.STAY;

public class Game {
    private final Dealer dealer;
    private final List<Player> players;

    public Game(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public List<Player> play() {
        // TODO: clear state of players

        dealer.dealInitialCards(players);

        eachPlayerPlaysHand(players);
        dealerPlaysHand();

        if (hasDealerWon(players)
        ) {
            return Collections.emptyList();
        } else {
            return List.of(players.get(0));
        }
    }

    private void eachPlayerPlaysHand(List<Player> players) {
        for (Player player : players) {
            do {
                if (player.nextActionType().equals(HIT)) {
                    dealer.dealCardTo(player);
                } else if (player.nextActionType().equals(STAY)) {
                    player.stay();
                }
            } while (!player.isBusted() && !player.isStaying());
        }
    }

    private void dealerPlaysHand() {
        do {
            if (dealer.totalHandValue() < 17 && atLeastOnePlayerHasNotBusted(players)) {
                dealer.dealCardTo(dealer);
            } else {
                dealer.stay();
            }
        } while (!dealer.isBusted() && !dealer.isStaying());
    }

    private boolean atLeastOnePlayerHasNotBusted(List<Player> players) {
        return players.stream()
                .anyMatch(player -> !player.isBusted());
    }

    private boolean hasDealerWon(List<Player> players) {
        Player player = players.get(0);

        return allPlayersBust(players)
                || !dealer.isBusted()
                && dealer.totalHandValue() > player.totalHandValue();
    }

    private boolean allPlayersBust(List<Player> players) {
        return players.stream()
                .allMatch(Player::isBusted);
    }
}