package fr.kybox.game.mode;

import fr.kybox.controller.Controller;
import fr.kybox.game.Game;
import fr.kybox.game.player.ai.AI;
import fr.kybox.utils.SecretCode;
import fr.kybox.utils.Settings;
import fr.kybox.view.Display;

public class Defender {

    private int[] secretCode;
    private AI computer;
    private Controller controller;

    public Defender(){

        controller = new Controller();

        String code = controller.getSecretCode(Settings.getBoxes(), Settings.getMaxNumbers());
        secretCode = SecretCode.convertToDigit(code);

        startGame();
    }

    private void startGame(){


        computer = new AI(Settings.getBoxes(), Settings.getMaxNumbers());

        boolean gameOver = false;

        do{
            Display.info("-----------------------------");
            Display.info("Proposition : ");

            String code = computer.generate();
            System.out.println(code);

            if(!SecretCode.isEqual(secretCode, code)) {

                Display.info("Réponse :");
                computer.setClues(controller.getClues(Game.GAME_TYPE, Settings.getBoxes()));
            }
            else gameOver = true;
        }
        while (!gameOver);

        gameOver();
    }

    private void gameOver(){

        Display.leading("");
        Display.leading("+----------------------------------------------------+");
        Display.leading("| L'ordinateur a découvert votre combinaison secrête |");
        Display.leading("+----------------------------------------------------+");
        Display.leading("  Nombre d'essais : " + computer.getTrials());
        Display.leading("");
    }
}
