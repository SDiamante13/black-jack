package tech.pathtoprogramming.blackjack;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static tech.pathtoprogramming.blackjack.ActionType.HIT;
import static tech.pathtoprogramming.blackjack.ActionType.STAY;

@Slf4j
public class Game {
    private final Dealer dealer;
    private final List<Player> players;

    public Game(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public List<Player> play() {
        // TODO: clear state of players
        dealer.shuffleDeck();
        dealer.dealInitialCards(players);

        eachPlayerPlaysHand(players);
        dealerPlaysHand();

        List<Player> winners = allPlayersBust(players) ? emptyList() : determineWinners(players);

        log.info("The winners are {}", winners.stream()
                .map(Player::name)
                .collect(Collectors.joining(",")));

        return winners;
    }

    private void eachPlayerPlaysHand(List<Player> players) {
        for (Player player : players) {
            log.info("PLAYER - {}'s turn: ", player.name());
            do {
                log.info("{}'s hand: {}", player.name(), player.showCards());
                if (player.nextActionType().equals(HIT)) {
                    dealer.dealCardTo(player);
                    log.info("{} hits!", player.name());
                } else if (player.nextActionType().equals(STAY)) {
                    player.stay();
                    log.info("{} stays!", player.name());
                }
            } while (!player.isBusted() && !player.isStaying());
            if (player.isBusted()) {
                log.info("{} busted! hand: {}", player.name(), player.showCards());
            }
        }
    }

    private void dealerPlaysHand() {
        log.info("DEALER - {}'s turn: ", dealer.name());
        do {
            log.info("{}'s hand: {}", dealer.name(), dealer.showCards());
            if (dealer.totalHandValue() < 17 && atLeastOnePlayerHasNotBusted(players)) {
                dealer.dealCardTo(dealer);
                log.info("{} hits!", dealer.name());
            } else {
                dealer.stay();
                log.info("{} stays!", dealer.name());
            }
        } while (!dealer.isBusted() && !dealer.isStaying());
        if (dealer.isBusted()) {
            log.info("{} busted! hand: {}", dealer.name(), dealer.showCards());
        }
    }

    private boolean atLeastOnePlayerHasNotBusted(List<Player> players) {
        return players.stream()
                .anyMatch(player -> !player.isBusted());
    }

    private boolean allPlayersBust(List<Player> players) {
        return players.stream()
                .allMatch(Player::isBusted);
    }

    // TODO: Fix bug where Simon is winning when he busts
    private List<Player> determineWinners(List<Player> players) {
        return players.stream()
                .filter(this::isPlayerWinner)
                .collect(Collectors.toList());
    }

    private boolean isPlayerWinner(Player player) {
        return player.totalHandValue() > dealer.totalHandValue()
                || !player.isBusted()
                && dealer.isBusted();
    }
}