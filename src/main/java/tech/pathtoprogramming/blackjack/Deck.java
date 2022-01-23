package tech.pathtoprogramming.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.addAll;

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


    // TODO: move to game object
    public void deal(Player ... players) {
        if (players == null || players.length == 0) {
            throw new NoPlayersException();
        }
    }

    public int cardCount() {
        return cards.size();
    }

    public Card lookAtTopCard() {
        return cards.get(0);
    }

    public Card draw() {
        return cards.remove(0);
    }
}
