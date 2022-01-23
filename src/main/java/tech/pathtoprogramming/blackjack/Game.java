package tech.pathtoprogramming.blackjack;

import java.util.Arrays;

public class Game {
    private final Deck deck;
    private final Player[] players;

    public Game(Deck deck, Player[] players) {
        this.deck = deck;
        this.players = players;
    }

    public void deal() {
        if (players == null || players.length == 0) {
            throw new NoPlayersException();
        }

        Arrays.stream(players).forEach(player -> player.addCard(deck.drawCard()));
        Arrays.stream(players).forEach(player -> player.addCard(deck.drawCard()));
    }
}