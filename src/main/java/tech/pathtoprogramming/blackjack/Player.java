package tech.pathtoprogramming.blackjack;

import java.util.Scanner;

public class Player {
    private final String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
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
        return hand.totalValue() > 21;
    }

    public ActionType getPlayerActionType() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("HIT or STAY");

        String actionType = myObj.nextLine();
        return ActionType.valueOf(actionType);
    }
}
