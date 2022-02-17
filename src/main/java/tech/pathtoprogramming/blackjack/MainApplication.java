package tech.pathtoprogramming.blackjack;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MainApplication {
    public static void main(String[] args) {
        Game game = new Game(
                new Dealer(
                        "George",
                        new Deck()
                ), List.of(
                new Player("Simon", new Hand(), new ActionInput()),
                new Player("Tina", new Hand(), new ActionInput())
        )
        );
        game.play();
    }
}
