package fr.kybox.game.mode;

import fr.kybox.controller.Controller;
import fr.kybox.game.Game;
import fr.kybox.game.player.AI;
import fr.kybox.utils.SecretCode;
import fr.kybox.utils.Settings;

public class Defender {

    private int[] secretCode;

    private Controller controller;

    public Defender(){

        controller = new Controller();

        String code = controller.getSecretCode(Settings.getBoxes(), Settings.getMaxNumbers());
        secretCode = SecretCode.convertToDigit(code);

        startGame();
    }

    private void startGame(){

        boolean gameOver = false;
        AI computer = new AI(Settings.getBoxes(), Settings.getMaxNumbers(), Game.GAME_TYPE);

        do{
            System.out.println("-------------------------");
            System.out.print("Proposition : ");

            String code = computer.generate();
            System.out.println(code);

            if(!SecretCode.isEqual(secretCode, code)) {

                System.out.println("Réponse :");
                computer.setClues(controller.getClues(Game.GAME_TYPE, Settings.getBoxes()));
            }
            else gameOver = true;
        }
        while (!gameOver);

        System.out.println("");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| L'ordinateur a découvert votre combinaison secrête |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("");
    }
}
