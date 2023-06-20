package ca.sheridancollege.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarGame extends Game {

    private GroupOfCards deck;
    private List<GroupOfCards> playerHands;
    Player player;

    public WarGame() {
        super("War");

//        Player player = null;
        deck = new GroupOfCards(1, player);
        playerHands = new ArrayList<>();
    }

    @Override
    public void play() {
        System.out.println("Welcome to the game of War!");

        // Initialize deck
        initializeDeck();

        // Shuffle deck
        deck.shuffle();

        // Distribute cards to players
        distributeCards();

        // Play the game until there is a winner
        while (!isGameOver()) {
            playRound();
        }

        // Declare the winner
        declareWinner();
    }

    private void initializeDeck() {
        // Create cards and add them to the deck
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (int rank = 2; rank <= 14; rank++) {
            for (String suit : suits) {
                Card card = new PlayingCard(rank, suit);
                deck.getCards().add(card);
            }
        }
    }

    private void distributeCards() {
        int numPlayers = getPlayers().size();
        System.out.println(getPlayers().size());
        int numCardsPerPlayer = deck.getCards().size() / numPlayers;
        System.out.println(numCardsPerPlayer);

        for (int i = 0; i < numPlayers; i++) {
            GroupOfCards hand = new GroupOfCards(numCardsPerPlayer, player);
            for (int j = 0; j < numCardsPerPlayer; j++) {
                Card card = deck.getCards().remove(0);
                hand.getCards().add(card);
                playerHands.get(i).getCards().add(card);
            }
//            playerHands.get(i).getCards().add(hand.getCards());
        }
//        System.out.println(playerHands.size());
    }

    private boolean isGameOver() {
        int numPlayers = getPlayers().size();
        int numEmptyHands = 0;

        for (GroupOfCards hand : playerHands) {
            if (hand.getCards().isEmpty()) {
                numEmptyHands++;
            }
        }

        return numEmptyHands == numPlayers - 1;
    }

    private void playRound() {
        List<Card> roundCards = new ArrayList<>();

        // Each player plays a card
        for (GroupOfCards hand : playerHands) {
            System.out.println("********---hand size for " +hand.getOwner().getName() + "  "+ hand.getCards().size());
            Card card = hand.getCards().get(0);
            hand.getCards().remove(0);
//            System.out.println(hand.getOwner().getName()+"  "+card.toString());
            roundCards.add(card);
            System.out.println("Player " + hand.getOwner().getName() + " plays: " + card.toString());
//            System.out.println("hand size for " +hand.getOwner().getName() + "  "+ hand.getCards().size());

        }
        if (roundCards.get(0).getRank() == roundCards.get(1).getRank()){
            System.out.println("EQUAL");
        }

        // Find the highest-ranked card
        int highestRank = 0;
        for (Card card : roundCards) {
            int rank = getRankValue(card);
            if (rank > highestRank) {
                highestRank = rank;
            }
        }

        // Find the players with the highest-ranked card
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < roundCards.size(); i++) {
            Card card = roundCards.get(i);
            int rank = getRankValue(card);
            if (rank == highestRank) {
                winners.add(i);
            }
        }

        // Award the cards to the winners
//        System.out.println("------"+ winners + "------");
        if (winners.size()==1) {
            GroupOfCards winningHand = playerHands.get(winners.get(0));
            for (Card card : roundCards) {
                winningHand.getCards().add(card);
            }
            System.out.println("Player " + playerHands.get(winners.get(0)).getOwner().getName() + " wins the round!");
        }
        else {
            System.out.println("\t It WAR COME ONN!!!");
//            playerHands.get(0).getCards().add(roundCards.get(0));
//            playerHands.get(1).getCards().add(roundCards.get(1));
            for (GroupOfCards hand : playerHands) {
                Card card1 = hand.getCards().get(0);
                Card card2 = hand.getCards().get(1);
                hand.getCards().remove(0);
                hand.getCards().remove(0);
//                System.out.println(hand.getOwner().getName()+"  "+card1.toString());
                roundCards.add(card1);
                roundCards.add(card2);

                System.out.println("Player " + hand.getOwner().getName() + " plays: " + card1.toString());
            }
            if (roundCards.get(2).getRank() == roundCards.get(4).getRank()){
                System.out.println("EQUAL AGAIN!!!");

            }if (roundCards.get(2).getRank() > roundCards.get(4).getRank()){
                GroupOfCards winningHand = playerHands.get(winners.get(0));
                for (Card card:roundCards){
                    winningHand.getCards().add(card);
                    System.out.println(playerHands.get(0).getOwner().getName()+" won the War");
                }
            }else{
                    GroupOfCards winningHand = playerHands.get(winners.get(1));
                    for (Card card:roundCards){
                        winningHand.getCards().add(card);
                        System.out.println(playerHands.get(1).getOwner().getName()+" won the War");
                    }
            }


        }
    }

    private int getRankValue(Card card) {
        if (card instanceof PlayingCard) {
            return ((PlayingCard) card).getRank();
        }
        return 0;
    }

    @Override
    public void declareWinner() {
        int maxScore = 0;
        Player winner = null;

        // Find the player with the highest score
        for (Player player : getPlayers()) {
            int playerScore = getPlayerScore(player);
            if (playerScore > maxScore) {
                maxScore = playerScore;
                winner = player;
            }
        }

        // Declare the winner
        if (winner != null) {
            communicateWin(winner);
        } else {
            System.out.println("No winner in the game.");
        }

        // Display player scores
//        displayPlayerScores();
    }

    private void communicateWin(Player winner) {
        System.out.println("The winner of the game is."+ winner.getName());
    }

    private int getPlayerScore(Player player) {
        int playerScore = 0;
        for (GroupOfCards hand : playerHands) {
            if (hand.getSize() == player.getScore()) {
                playerScore += hand.getCards().size();
            }
        }
        return playerScore;
    }
    public void displayPlayerScores() {
        for (GroupOfCards hand : playerHands) {
            int score = hand.getCards().size();
            System.out.println("Player " + hand.getOwner().getName() + " score: " + score);
        }
    }
    public void addPlayer(Player player) {
        playerHands.add(new GroupOfCards(0, player));
        super.getPlayers().add(player);
    }
  

    private Card findHighestCard(ArrayList<Card> cards) {
        Card highestCard = cards.get(0);
        for (int i = 1; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            if (currentCard.getRank() > highestCard.getRank()) {
                highestCard = currentCard;
            }
        }
        return highestCard;
    }
    public static void main(String[] args) {
        WarGame game = new WarGame();
        Scanner input = new Scanner(System.in);
        System.out.println("--------Lets Get Started--------");
        System.out.println("Enter the name of the First player:");
        String player_1 = input.next();
        System.out.println("Enter the name of the First player:");
        String player_2 = input.next();

        // Create and add players
        Player player1 = new Player(player_1);
        Player player2 = new Player(player_2);

        game.addPlayer(player1);
        game.addPlayer(player2);

        System.out.println(game.playerHands.get(0).getOwner().getName());
        System.out.println(game.playerHands.get(1).getOwner().getName());

        game.play();

        game.displayPlayerScores();
    }

}
