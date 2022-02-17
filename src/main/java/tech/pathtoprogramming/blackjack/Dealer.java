package tech.pathtoprogramming.blackjack;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Dealer extends Player {

    private final Deck deck;

    public Dealer(String name, Deck deck) {
        super(name, new Hand());
        this.deck = deck;
    }

    public void dealCardTo(Player player) {
        Card card = deck.drawCard();
        player.addCard(card);
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

    public void shuffleDeck() {
        deck.shuffle();
    }
}