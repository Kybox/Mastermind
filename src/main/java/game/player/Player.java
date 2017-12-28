package main.java.game.player;

/**
 * @author Kybox
 * @version 1.0
 */

public interface Player {

    /**
     * Get the secret combination created by the player
     * @return  The secret combination
     */
    int[] setSecretCode();

    /**
     * Get a combination for a game tour
     * @return  A new combination
     */
    String getNewCombination();

    /**
     * Get the clues provided by the player
     * @param combination   The player's combination
     * @param secretCode    The secret combination
     * @return              The clues
     */
    String getClues(String combination, int[] secretCode);

    /**
     * Set the clues
     * @param clues The clues to keep
     */
    void setClues(String clues);
}
