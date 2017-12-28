package main.java.game.mode;

/**
 * @author Kybox
 * @version 1.0
 */

public interface GameMode {

    /**
     * Start the game after initializing the different parameters
     */
    void startGame();

    /**
     * Performs a game round
     * @return  True if the game is over, otherwise false.
     */
    boolean gameTour();

    /**
     * Check if the game is won
     * @return  True if the game is won, otherwise false.
     */
    boolean isGameWon();
}