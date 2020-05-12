package blackjack;

/**
 * The type Application.
 */
public class Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        BlackJackVariation bj = new BlackJackVariation();
        bj.playGame();
    }

}