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
    private boolean win;
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

        Display.gameOver(win, Arrays.toString(secretCode), trials - 1);
    }

    @Override
    public boolean gameTour(){

        trials++;

        if(trials > maxTrials) {
            win = false;
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
                win = true;
                finished = true;
            }
        }

        return finished;
    }

    /**
     * Generate a secret combinaition
     */
    private void setSecretCode(){
        secretCode = human.setSecretCode();
        //secretCode = SecretCode.convertToDigit(code);
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
