package tech.pathtoprogramming.blackjack;

import java.util.Scanner;

public class ActionInput {
    public ActionInput() {
    }

    public ActionType nextActionType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("HIT or STAY");

        String actionType = scanner.nextLine();
        return ActionType.valueOf(actionType);
    }
}