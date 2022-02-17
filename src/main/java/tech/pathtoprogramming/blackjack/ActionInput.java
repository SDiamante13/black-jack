package tech.pathtoprogramming.blackjack;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ActionInput {
    public ActionInput() {
    }

    public ActionType nextActionType() {
        Scanner scanner = new Scanner(System.in);
        log.info("HIT or STAY");

        String actionType = scanner.nextLine();
        return ActionType.valueOf(actionType);
    }
}