package fr.kybox.controller;

import fr.kybox.game.Game;
import fr.kybox.utils.CheckInput;

import java.util.Scanner;

public class Controller {

    private final String GAME_TYPE = "game";
    private final String GAME_MODE = "mode";
    private final int GAME1 = 1;
    private final int GAME2 = 2;

    private Scanner scanner;

    public Controller(){

        scanner = new Scanner(System.in);
    }

    public int getGame(){

        System.out.println("-----------------------------");
        System.out.println("Sélectionner un jeu :");
        System.out.println("[1] - Mastermind");
        System.out.println("[2] - Recherche +/-");

        String gameMsg = "Saissir le numéro correspondant au jeu souhaité";
        return getParamsInput(GAME_TYPE, gameMsg);
    }

    public int getMode(){

        System.out.println("-----------------------------");
        System.out.println("Sélectionner un mode de jeu :");
        System.out.println("[1] - Challenger");
        System.out.println("[2] - Duel");
        System.out.println("[3] - Défenseur");

        String modeMsg = "Saissir le numéro correspondant au mode de jeu souhaité";
        return getParamsInput(GAME_MODE, modeMsg);

    }

    private int getParamsInput(String type, String msg){

        int nbChoices = 0;
        int input;

        if(type.equals(GAME_TYPE)) nbChoices = 2;
        else if(type.equals(GAME_MODE)) nbChoices = 3;

        while (true){

            String line = scanner.nextLine();

            if (!CheckInput.params(line, nbChoices)) System.out.println(msg);
            else {
                input = Integer.parseInt(line);
                break;
            }
        }
        return input;
    }

    public String getGameInput(int nbBoxes, int maxNumbers){

        String line;

        while (true){

            line = scanner.nextLine();

            if(!CheckInput.isApproved(line, nbBoxes, maxNumbers)) continue;
            else break;
        }

        return line;
    }

    public String getSecretCode(int nbBoxes, int maxNumbers){

        System.out.println("-----------------------------");
        System.out.println("Sélectionner votre combinaison secrête :");
        System.out.println("(" + nbBoxes + " chiffres compris entre 0 et " + (maxNumbers - 1) + ")");

        String line;

        while (true){

            line = scanner.nextLine();

            if(!CheckInput.isApproved(line, nbBoxes, maxNumbers)) continue;
            else break;
        }

        return line;
    }

    public String getClues(int nbBoxes, String code, int[] secretCode){

        String line = "";

        switch (Game.GAME_TYPE){
            case 1:
                while (true){
                    line = scanner.nextLine();
                    if(!CheckInput.getCluesGame1(line, code, secretCode)) continue;
                    else break;
                }
                break;

            case 2:
                while (true){
                    line = scanner.nextLine();
                    if(!CheckInput.getCluesGame2(line, nbBoxes)) continue;
                    else break;
                }
                break;
        }

        return line;
    }
}
