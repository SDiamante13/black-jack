package tech.pathtoprogramming.blackjack;

public class Dealer extends Player {

    private final Deck deck;

    public Dealer(String name, Deck deck) {
        super(name);
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void dealCardTo(Player player) {
        player.addCard(deck.drawCard());
    }
}