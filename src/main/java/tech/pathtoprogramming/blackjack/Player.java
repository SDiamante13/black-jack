package tech.pathtoprogramming.blackjack;

public class Player {
    private final String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int handCount() {
        return hand.handCount();
    }

    public int totalHandValue() {
        return hand.totalValue();
    }
}
