package main.java.game.mode;

import main.java.game.player.human.Human;
import main.java.game.player.ai.AI;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

public class Challenger implements IGameMode {

    private int[] secretCode;
    private int trials;
    private boolean win;
    private boolean finished;

    private final AI computer;
    private final int maxTrials;
    private final Human human;
    private final boolean autoManagement;

    /**
     * The Challenger constructor
     * @param   autoManagement  Indicates if the class manages itself the game rounds
     */
    public Challenger(boolean autoManagement){

        this.autoManagement = autoManagement;

        computer = new AI();
        human = new Human();
        maxTrials = Settings.getTrials();

        setSecretCode();
        startGame();
    }

    @Override
    public void startGame(){

        trials = 0;

        if(Settings.isDevMode())
            Display.info("Combinaison secrête : " + Arrays.toString(secretCode));

        if(autoManagement) {

            do { gameTour(); }
            while (!finished);

            Display.gameOver(win, Arrays.toString(secretCode), trials);
        }
    }

    @Override
    public boolean gameTour(){

        trials++;

        if(trials > maxTrials) {
            win = false;
            finished = true;
        }
        else {
            Display.info("------------------------");
            Display.info("Saisir une combinaison :");

            String combination = human.getGameInput();

            if (SecretCode.isEqual(secretCode, combination)) {
                win = true;
                finished = true;
            }
            else {
                Display.info("Réponse : " + computer.getClues(secretCode, combination));
            }
        }
        return finished;
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
