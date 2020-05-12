package blackjack;

import blackjack.cardpack.Card;
import blackjack.cardpack.Deck;
import blackjack.cardpack.Hand;

import java.util.Scanner;

/**
 * The type Black jack game variation.
 */
public class BlackJackVariation {

    private Deck deck;
    private Hand playersHand;
    private int gameScore;
    private int roundScore;
    private int round;
    private int roundLimit;
    private static Scanner in;

    /**
     * Instantiates a new Black jack variation.
     */
    public BlackJackVariation() {
        in = new Scanner(System.in);
    }

    /**
     * Play game.
     * Initiates game with the default 5 rounds;
     */
    public void playGame() {
        playGame(5);
    }

    /**
     * Play game.
     *
     * @param numberOfRounds the number of rounds
     */
    public void playGame(int numberOfRounds) {
        deck = new Deck(false);
        playersHand = new Hand();
        gameScore = 100;
        round = 1;
        roundLimit = numberOfRounds;
        boolean playerWishesToContinue;

        while (round <= roundLimit) {
            System.out.println("### Round " + round + " ###");

            playersHand.clear();
            deck.shuffle(); // replaces all cards and shuffles
            roundScore = 0;
            playerWishesToContinue = true;

            // deal two cards to player
            draw();
            draw();

            while ((roundScore <= 21) && playerWishesToContinue) {
                playerWishesToContinue = offerDraw();
            }

            System.out.println();
            if (roundScore > 21) {
                System.out.println("Bust! You went over 21! 21 will be subtracted from your total score.");
                gameScore -= 21;
            } else {
                System.out.println("Well done! You stayed below 21! " +
                        (21 - roundScore) +
                        " will be subtracted from your total score.");
                gameScore -= (21 - roundScore);
            }
            System.out.println("\nGame Score: " + gameScore + "\n");
            round++;
        }
    }

    private boolean offerDraw() {
        String choice;
        System.out.println("\n\t" + playersHand);
        System.out.println("\tTotalling " + roundScore);
        System.out.println("\nDo you wish to draw another card? ('Y' or 'N'):");
        choice = in.nextLine().toUpperCase();
        if ("Y".equals(choice)) {
            draw();
            return true;
        }
        return false;
    }

    private void draw() {
        Card drawnCard = deck.dealCard();
        playersHand.addCard(drawnCard);
        if (drawnCard.getRank() >= 10)
            roundScore += 10;
        else
            roundScore += drawnCard.getRank();
        System.out.println("You drew the " + drawnCard);
    }

}