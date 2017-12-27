package main.java.game.mode;

import main.java.game.player.Player;
import main.java.game.player.computer.Computer;
import main.java.game.player.human.Human;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

public class Defender implements GameMode {

    private int trials;
    private boolean gameWon;
    private boolean finished;

    private int[] secretCode;
    private final int maxTrials;

    private final Player computer;
    private final Player human;

    /**
     * The Defender constructor
     */
    public Defender(){

        human = new Human();
        maxTrials = Settings.getTrials();
        computer = new Computer();
        setSecretCode();
    }

    @Override
    public void startGame(){

        do { gameTour(); }
        while (!finished);

        Display.gameOver(gameWon, Arrays.toString(secretCode), trials - 1, false);
    }

    @Override
    public boolean gameTour(){

        trials++;

        if(trials > maxTrials) {
            gameWon = false;
            finished = true;
        }
        else {

            String combination = computer.getNewCombination();
            Display.computerCombination(combination);

            if (!SecretCode.isEqual(secretCode, combination)) {

                Display.info("");
                Display.info("Votre réponse :");
                String clues = human.getClues(combination, secretCode);
                computer.setClues(clues);
            }
            else {
                gameWon = true;
                finished = true;
            }
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

        secretCode = human.setSecretCode();
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
