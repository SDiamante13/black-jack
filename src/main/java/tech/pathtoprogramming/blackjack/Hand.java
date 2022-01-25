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

    public TotalValue totalValue() {
        int alternateValue = Integer.MAX_VALUE;
        int sum = cards.stream()
                .map(Card::value)
                .reduce(0, Integer::sum);

        if (cards.contains(Card.ACE)) {
            long aceCount = getAceCount(cards);
            if (aceCount != 1) {
                sum -= 10 * (aceCount - 1);
            }
            alternateValue = sum - 10;
        }

        return new TotalValue(sum, alternateValue);
    }

    private long getAceCount(List<Card> cards) {
        return cards.stream()
                .filter(card -> card.cardRank().equals("A"))
                .count();
    }

    boolean isBusted() {
        return totalValue().getValue() > 21 && totalValue().getAlternateValue() > 21;
    }
}