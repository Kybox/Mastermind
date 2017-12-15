package main.java.game.mode;

import main.java.controller.Controller;
import main.java.game.Game;
import main.java.utils.SecretCode;
import main.java.utils.Settings;

import java.util.Arrays;

public class Challenger {

    private int[] secretCode;

    public Challenger(){

        setSecretCode();

        switch (Game.GAME_TYPE){
            case 1:
                System.out.println("Début de la partie Main en mode Challenger");
                break;

            case 2:
                System.out.println("Début de la partie Recherche +/- en mode Challenger");
                break;
        }

        startGame();
    }

    private void startGame(){

        Controller controller = new Controller();
        boolean gameOver = false;

        System.out.println("Combinaison secrête : " + Arrays.toString(secretCode));

        do{
            System.out.println("-------------------------");
            System.out.println("Saisir une combinaison : ");

            String trial = controller.getGameInput(Settings.getBoxes(), Settings.getMaxNumbers());

            System.out.print("Proposition : " + trial);
            System.out.print(" -> Réponse : ");

            if(SecretCode.isEqual(secretCode, trial)) gameOver = true;
            else System.out.println(SecretCode.getClues(Game.GAME_TYPE, secretCode, trial));

        }
        while(!gameOver);

        System.out.println("Bravo, vous avez trouvé la combinaison secrête !");
    }

    private void setSecretCode(){

        int nbBoxes = Settings.getBoxes();
        int nbNumbers = Settings.getMaxNumbers();
        secretCode = SecretCode.generate(nbBoxes, nbNumbers);
    }
}
