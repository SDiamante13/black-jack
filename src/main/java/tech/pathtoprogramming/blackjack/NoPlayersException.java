package tech.pathtoprogramming.blackjack;

public class NoPlayersException extends RuntimeException {
    public NoPlayersException() {
        super("No players to deal cards to");
    }
}
