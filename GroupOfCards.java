package ca.sheridancollege.project1;
import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    private ArrayList<Card> cards;
    private int size;
    private Player player;

    public GroupOfCards(int size, Player player) {
        this.size = size;
        cards = new ArrayList<>();
        this.player = player;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public Player getOwner() {
        return player;
    }
}
