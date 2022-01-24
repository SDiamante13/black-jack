package tech.pathtoprogramming.blackjack;

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

    public String play() {
        // TODO: clear state of players

        dealOneCardToAllPlayers();
        dealOneCardToAllPlayers();

        eachPlayerPlaysHand(players);

        dealerPlaysHand();

        if (hasDealerWon(players.get(0))
        ) {
            return dealer.name();
        } else {
            return players.get(0).name();
        }
    }

    public void dealOneCardToAllPlayers() {
        if (players == null || players.isEmpty()) {
            throw new NoPlayersException();
        }

        players.forEach(dealer::dealCardTo);
        hit(dealer);
    }

    private void eachPlayerPlaysHand(List<Player> players) {
        Player player = players.get(0);

        do {
            if (player.nextActionType().equals(HIT)) {
                hit(player);
            } else if (player.nextActionType().equals(STAY)) {
                player.stay();
            }
        } while (!player.isBusted() && !player.isStaying());
    }

    private void hit(Player activePlayer) {
        dealer.dealCardTo(activePlayer);
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

    private boolean hasDealerWon(Player activePlayer) {
        return activePlayer.isBusted()
                || !dealer.isBusted()
                && dealer.totalHandValue() > activePlayer.totalHandValue();
    }
}