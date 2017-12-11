package fr.kybox.game.mode;

import fr.kybox.controller.Controller;
import fr.kybox.utils.SecretCode;
import fr.kybox.utils.Settings;

import java.util.Arrays;

public class Challenger {

    private int game;
    private int[] secretCode;

    public Challenger(int game){

        this.game = game;
        setSecretCode();

        switch (game){
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
        boolean gameOver = false;

        System.out.println("Combinaison secrête : " + Arrays.toString(secretCode));

        do{
            System.out.println("-------------------------");
            System.out.println("Saisir une combinaison : ");

            String trial = controller.getGameInput(Settings.getBoxes());

            System.out.print("Proposition : " + trial);
            System.out.print(" -> Réponse : ");

            if(SecretCode.isEqual(secretCode, trial)) gameOver = true;
            else System.out.println(SecretCode.getClues(game, secretCode, trial));

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
