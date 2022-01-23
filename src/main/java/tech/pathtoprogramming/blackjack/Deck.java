package tech.pathtoprogramming.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        for (Card card : Card.values()) {
            cards.add(card);
        }
        for (Card card : Card.values()) {
            cards.add(card);
        }
        for (Card card : Card.values()) {
            cards.add(card);
        }
        for (Card card : Card.values()) {
            cards.add(card);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int count() {
        return cards.size();
    }

    public Card lookAtTopCard() {
        return cards.get(0);
    }
}
