package ca.sheridancollege.project1;
public class PlayingCard extends Card {
    private String suit;

    public PlayingCard(int rank, String suit) {
        super(rank);
        this.suit = suit;
    }

    @Override
    public String toString() {
        String rankString;

        if (getRank() >= 2 && getRank() <= 10) {
            rankString = String.valueOf(getRank());
        } else {
            switch (getRank()) {
                case 11:
                    rankString = "Jack";
                    break;
                case 12:
                    rankString = "Queen";
                    break;
                case 13:
                    rankString = "King";
                    break;
                case 14:
                    rankString = "Ace";
                    break;
                default:
                    rankString = "Unknown";
            }
        }

        return rankString + " of " + suit;
    }
}
