package main.java.game.mode;

import main.java.controller.Controller;
import main.java.game.GameOver;
import main.java.game.player.ai.AI;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

public class Defender {

    private int[] secretCode;
    private AI computer;
    private Controller controller;
    private GameOver gameOver;
    private int maxTrials;

    public Defender(GameOver gameOver){

        this.gameOver = gameOver;
        controller = new Controller();
        maxTrials = Settings.getTrials();
        String code = controller.getSecretCode(Settings.getBoxes(), Settings.getMaxNumbers());
        secretCode = SecretCode.convertToDigit(code);
        startGame();
    }

    private void startGame(){

        computer = new AI(Settings.getBoxes(), Settings.getMaxNumbers());
        boolean blnGameOver = false;
        boolean win = false;
        String code = null;
        int trials = 0;

        do{
            trials++;

            if(trials > maxTrials) {
                win = false;
                blnGameOver = true;
            }
            else {
                Display.info("-----------------------------");
                Display.info("Proposition : ");

                code = computer.generate();
                System.out.println(code);

                if (!SecretCode.isEqual(secretCode, code)) {

                    Display.info("Réponse :");
                    String clues = controller.getClues(Settings.getBoxes(), code, secretCode);
                    computer.setClues(clues);
                } else blnGameOver = true;
            }
        }
        while (!blnGameOver);

        gameOver.display(win, Arrays.toString(secretCode), trials - 1);
    }
}
