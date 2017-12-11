package fr.kybox.controller;

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

    public String getGameInput(int nbNumbers){

        String line;

        while (true){

            line = scanner.nextLine();

            if(!CheckInput.isApproved(line, nbNumbers)) continue;
            else break;
        }

        return line;
    }
}
