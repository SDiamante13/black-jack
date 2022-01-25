package tech.pathtoprogramming.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public int handCount() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int totalValue() {
        return cards.stream()
                .map(Card::value)
                .reduce(0, Integer::sum);
    }

    boolean isBusted() {
        return totalValue() > 21;
    }
}