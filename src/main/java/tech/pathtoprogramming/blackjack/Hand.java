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

    // TODO: Calculate two sums if an A is present in the hand
    public TotalValue totalValue() {
        int sum = 0;
        for (Card card : cards) {
            int value = card.value();
            sum = sum + value;
        }

        return new TotalValue(sum, Integer.MAX_VALUE);
    }

    boolean isBusted() {
        return totalValue().getValue() > 21;
    }
}