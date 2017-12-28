package main.java.game.mode;

import main.java.game.player.Player;
import main.java.game.player.computer.Computer;
import main.java.game.player.human.Human;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

/**
 * @author Kybox
 * @version 1.0
 */

public class Challenger implements GameMode {

    private int trials;
    private boolean gameWon;
    private boolean finished;
    private int[] secretCode;

    private final Player computer;
    private final Player human;
    private final int maxTrials;

    /**
     * The Challenger constructor
     */
    public Challenger(){

        computer = new Computer();
        human = new Human();
        maxTrials = Settings.getTrials();
        setSecretCode();
    }

    @Override
    public void startGame(){

        trials = 0;

        if(Settings.isDevMode())
            Display.info("Combinaison secrête : " + Arrays.toString(secretCode));

        do { gameTour(); }
        while (!finished);

        Display.gameOver(gameWon, Arrays.toString(secretCode), trials, false);

    }

    @Override
    public boolean gameTour(){

        trials++;

        if(trials > maxTrials) {
            gameWon = false;
            finished = true;
            trials = maxTrials;
        }
        else {
            Display.typeCombination();
            String combination = human.getNewCombination();

            if (SecretCode.isEqual(secretCode, combination)) {
                gameWon = true;
                finished = true;
            }
            else human.setClues(computer.getClues(combination, secretCode));
        }
        return finished;
    }

    @Override
    public boolean isGameWon(){
        return gameWon;
    }

    /**
     * Generate a secret combinaition
     */
    private void setSecretCode(){

        secretCode = SecretCode.generate(Settings.getKeys(), Settings.getMaxNumber());
    }

    /**
     * Retrieves the generated secret combination
     * @return  The secret combination
     */
    public int[] getSecretCode(){
        return secretCode;
    }

    /**
     * Retrieves the number of trials
     * @return  Trials
     */
    public int getTrials(){
        return trials;
    }
}
