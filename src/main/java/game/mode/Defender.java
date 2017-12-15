package main.java.game.mode;

import main.java.controller.Controller;
import main.java.game.GameOver;
import main.java.game.player.ai.AI;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

public class Defender {

    private int[] secretCode;
    private AI computer;
    private Controller controller;
    private GameOver gameOver;

    public Defender(GameOver gameOver){

        this.gameOver = gameOver;
        controller = new Controller();
        String code = controller.getSecretCode(Settings.getBoxes(), Settings.getMaxNumbers());
        secretCode = SecretCode.convertToDigit(code);
        startGame();
    }

    private void startGame(){

        computer = new AI(Settings.getBoxes(), Settings.getMaxNumbers());
        boolean gameOver = false;
        String code = null;

        do{
            if(!gameOver) {
                Display.info("-----------------------------");
                Display.info("Proposition : ");

                code = computer.generate();
                System.out.println(code);

                if (!SecretCode.isEqual(secretCode, code)) {

                    Display.info("Réponse :");

                    String clues = controller.getClues(Settings.getBoxes(), code, secretCode);
                    computer.setClues(clues);
                }
                else gameOver = true;
            }
        }
        while (!gameOver);

        gameOver(code);
    }

    private void gameOver(String code){

        Display.leading("");
        Display.leading("+----------------------------------------------------+");
        Display.leading("| L'ordinateur a découvert votre combinaison secrête |");
        Display.leading("+----------------------------------------------------+");
        Display.leading("  Votre code secret : " + code);
        Display.leading("  Nombre d'essais : " + computer.getTrials());
        Display.leading("");
    }
}
