package tech.pathtoprogramming.blackjack;

public enum Card {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE_HIGH("A", 11);

    Card(final String rank, final int value) {
        this.rank = rank;
        this.value = value;
    }

    private String rank;
    private int value;

    public String cardRank() {
        return rank;
    }
}
