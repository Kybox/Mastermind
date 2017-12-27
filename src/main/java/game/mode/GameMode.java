package main.java.game.mode;

public interface GameMode {

    /**
     * Start the game after initializing the different parameters
     */
    void startGame();

    /**
     * Performs a game round
     * @return  True if the game is over otherwise
     */
    boolean gameTour();

    boolean isGameWon();
}