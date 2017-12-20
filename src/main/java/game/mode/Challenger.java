package main.java.game.mode;

import main.java.controller.Controller;
import main.java.game.GameOver;
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
    private final GameOver gameOver;
    private final Controller controller;
    private final boolean autoManagement;

    /**
     * The Challenger constructor
     * @param   gameOver        A GameOver object for the game
     * @param   autoManagement  Indicates if the class manages itself the game rounds
     */
    public Challenger(GameOver gameOver, boolean autoManagement){

        this.gameOver = gameOver;
        this.autoManagement = autoManagement;

        computer = new AI();
        controller = new Controller();
        maxTrials = Settings.getTrials();

        setSecretCode();
        startGame();
    }

    @Override
    public void startGame(){

        trials = 0;

        Display.info("Combinaison secrête : " + Arrays.toString(secretCode));

        if(autoManagement) {

            do { gameTour(); }
            while (!finished);

            gameOver.display(win, Arrays.toString(secretCode), trials);
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

            String combination = controller.getGameInput();

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
        secretCode = SecretCode.generate(Settings.getBoxes(), Settings.getMaxNumbers());
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
