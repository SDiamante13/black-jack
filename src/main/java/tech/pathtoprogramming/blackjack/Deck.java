package tech.pathtoprogramming.blackjack;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        addAll(this.cards, Card.values());
        addAll(this.cards, Card.values());
        addAll(this.cards, Card.values());
        addAll(this.cards, Card.values());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int cardCount() {
        return cards.size();
    }

    public Card lookAtTopCard() {
        return cards.get(0);
    }

    public Card drawCard() {
        return cards.remove(0);
    }
}
