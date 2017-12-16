package main.java.game.mode;

import main.java.controller.Controller;
import main.java.game.Game;
import main.java.game.GameOver;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

public class Challenger {

    private GameOver gameOver;
    private int[] secretCode;
    private int maxTrials;
    private int trials;
    private boolean win;
    private boolean finished;
    private Controller controller;
    private boolean autoManagement;

    public Challenger(GameOver gameOver){

        this.gameOver = gameOver;
        controller = new Controller();
        maxTrials = Settings.getTrials();
    }

    public void startGame(){

        trials = 0;

        Display.info("Combinaison secrête : " + Arrays.toString(secretCode));

        if(autoManagement) {

            do { gameTour(); }
            while (!finished);

            gameOver.display(win, Arrays.toString(secretCode), trials);
        }
    }

    public boolean gameTour(){

        trials++;

        if(trials > maxTrials) {
            win = false;
            finished = true;
        }
        else {
            Display.info("------------------------");
            Display.info("Saisir une combinaison :");

            String trial = controller.getGameInput();

            if (SecretCode.isEqual(secretCode, trial)) {
                win = true;
                finished = true;
            }
            else {
                Display.info("Réponse : " + SecretCode.getClues(Game.GAME_TYPE, secretCode, trial));
            }
        }

        return finished;
    }

    public void setSecretCode(){

        int nbBoxes = Settings.getBoxes();
        int nbNumbers = Settings.getMaxNumbers();
        secretCode = SecretCode.generate(nbBoxes, nbNumbers);
    }

    public int[] getSecretCode(){
        return secretCode;
    }

    public int getTrials(){
        return trials;
    }

    public void setAutoManagement(boolean auto){
        autoManagement = auto;
    }
}
