package main.java.game.mode;

import main.java.controller.Controller;
import main.java.game.GameOver;
import main.java.game.player.ai.AI;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

public class Defender {

    private AI computer;
    private int[] secretCode;
    private Controller controller;
    private GameOver gameOver;
    private int maxTrials;
    private boolean autoManagement;
    private int trials;
    private boolean win;
    private boolean finished;

    public Defender(GameOver gameOver){

        this.gameOver = gameOver;
        controller = new Controller();
        maxTrials = Settings.getTrials();
        computer = new AI(Settings.getBoxes(), Settings.getMaxNumbers());
    }

    public void startGame(){

        int trials = 0;

        if(autoManagement) {

            do { gameTour(); }
            while (!finished);

            gameOver.display(win, Arrays.toString(secretCode), trials - 1);
        }
    }

    public boolean gameTour(){

        trials++;

        if(trials > maxTrials) {
            win = false;
            finished = true;
        }
        else {
            Display.info("-----------------------------");
            Display.info("Proposition de l'ordinateur : ");

            String newCode = computer.generate();
            System.out.println(newCode);

            if (!SecretCode.isEqual(secretCode, newCode)) {

                Display.info("Votre réponse :");
                String clues = controller.getClues(Settings.getBoxes(), newCode, secretCode);
                computer.setClues(clues);
            }
            else {
                win = true;
                finished = true;
            }
        }

        return finished;
    }

    public void setSecretCode(){

        String code = controller.getSecretCode(Settings.getBoxes(), Settings.getMaxNumbers());
        secretCode = SecretCode.convertToDigit(code);
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
