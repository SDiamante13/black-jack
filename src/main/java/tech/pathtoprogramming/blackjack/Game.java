package tech.pathtoprogramming.blackjack;

import java.util.List;

public class Game {
    private final List<Player> players;
    private final Dealer dealer;

    public Game(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void dealOneCardToAllPlayers() {
        if (players == null || players.isEmpty()) {
            throw new NoPlayersException();
        }

        players.forEach(dealer::dealCardTo);
        dealer.dealCardTo(dealer);
    }

    public void start() {
        dealOneCardToAllPlayers();
        dealOneCardToAllPlayers();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }
}