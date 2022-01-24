package tech.pathtoprogramming.blackjack;

public class Player {
    private final String name;
    private ActionInput actionInput;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public Player(String name, ActionInput actionInput) {
        this(name);
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
        return hand.totalValue();
    }

    public boolean isBusted() {
        return hand.isHandBusted();
    }

    public ActionType nextActionType() {
        return actionInput.nextActionType();
    }
}
