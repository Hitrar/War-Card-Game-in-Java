package ca.sheridancollege.project1;
public abstract class Card {
    private int rank;

    public Card(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public abstract String toString();
}
