package tech.pathtoprogramming.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public int handCount() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int totalHandValue() {
        return cards.stream()
                .map(Card::value)
                .reduce(0, Integer::sum);
    }
}
