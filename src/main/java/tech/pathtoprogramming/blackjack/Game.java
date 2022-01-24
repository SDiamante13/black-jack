package tech.pathtoprogramming.blackjack;

import java.util.List;

import static tech.pathtoprogramming.blackjack.ActionType.HIT;
import static tech.pathtoprogramming.blackjack.ActionType.STAY;

public class Game {
    private final List<Player> players;
    private final Dealer dealer;
    private String winner;

    public Game(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void dealOneCardToAllPlayers() {
        if (players == null || players.isEmpty()) {
            throw new NoPlayersException();
        }

        players.forEach(dealer::dealCardTo);
        hit(dealer);
    }

    public void start() {
        dealOneCardToAllPlayers();
        dealOneCardToAllPlayers();

        Player activePlayer = players.get(0);

        while (!activePlayer.isBusted()) {
            if (activePlayer.nextActionType().equals(HIT)) {
                hit(activePlayer);
            } else if (activePlayer.nextActionType().equals(STAY)) {
                break;
            }
        }

        while (!dealer.isBusted()) {
            if (dealer.totalHandValue() < 17) {
                dealer.dealCardTo(dealer);
            } else {
                break;
            }
        }

        if (activePlayer.isBusted()
                || !dealer.isBusted() && dealer.totalHandValue() > activePlayer.totalHandValue()
        ) {
            winner = dealer.name();
        } else {
            winner = activePlayer.name();
        }
    }

    private void hit(Player activePlayer) {
        dealer.dealCardTo(activePlayer);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }

    public String winner() {
        return winner;
    }
}