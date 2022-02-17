package tech.pathtoprogramming.blackjack;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private final Hand hand;

    private ActionInput actionInput;
    private boolean stay;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
        this.stay = false;
    }

    public Player(String name, Hand hand, ActionInput actionInput) {
        this(name, hand);
        this.actionInput = actionInput;
    }

    public String name() {
        return name;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int handCount() {
        return hand.handCount();
    }

    public int totalHandValue() {
        if (hand.isBusted()) {
            return hand.totalValue().getValue();
        }

        return hand.totalValue().getValue() > 21
                ? hand.totalValue().getAlternateValue()
                : hand.totalValue().getValue();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public ActionType nextActionType() {
        return actionInput.nextActionType();
    }

    public boolean isStaying() {
        return stay;
    }

    public void stay() {
        this.stay = true;
    }

    public String showCards() {
        return hand.showCards().stream()
                .map(Card::cardRank)
                .collect(Collectors.joining(","));
    }
}
