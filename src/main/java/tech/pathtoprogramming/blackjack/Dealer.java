package tech.pathtoprogramming.blackjack;

import java.util.List;

public class Dealer extends Player {

    private final Deck deck;

    public Dealer(String name, Deck deck) {
        super(name);
        this.deck = deck;
    }

    public void dealCardTo(Player player) {
        player.addCard(deck.drawCard());
    }

    public void dealInitialCards(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new NoPlayersException();
        }

        players.forEach(this::dealCardTo);
        dealCardTo(this);

        players.forEach(this::dealCardTo);
        dealCardTo(this);
    }
}