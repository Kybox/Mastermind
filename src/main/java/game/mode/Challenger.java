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

    public Challenger(GameOver gameOver){

        setSecretCode();
        this.gameOver = gameOver;
        maxTrials = Settings.getTrials();

        switch (Game.GAME_TYPE){
            case 1:
                System.out.println("Début de la partie Mastermind en mode Challenger");
                break;

            case 2:
                System.out.println("Début de la partie Recherche +/- en mode Challenger");
                break;
        }

        startGame();
    }

    private void startGame(){

        Controller controller = new Controller();
        boolean blnGameOver = false;
        boolean win = false;
        int trials = 0;

        Display.info("Combinaison secrête : " + Arrays.toString(secretCode));

        do{
            trials++;

            if(trials > maxTrials) {
                win = false;
                blnGameOver = true;
            }
            else {
                Display.info("-------------------------");
                Display.info("Saisir une combinaison : ");

                String trial = controller.getGameInput(Settings.getBoxes(), Settings.getMaxNumbers());

                if (SecretCode.isEqual(secretCode, trial)) {
                    win = true;
                    blnGameOver = true;
                }
                else
                    Display.info("Réponse : " + SecretCode.getClues(Game.GAME_TYPE, secretCode, trial));
            }
        }
        while(!blnGameOver);

        gameOver.display(win, Arrays.toString(secretCode), trials);
    }

    private void setSecretCode(){

        int nbBoxes = Settings.getBoxes();
        int nbNumbers = Settings.getMaxNumbers();
        secretCode = SecretCode.generate(nbBoxes, nbNumbers);
    }
}
