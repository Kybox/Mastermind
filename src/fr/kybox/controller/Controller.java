package fr.kybox.controller;

import fr.kybox.utils.CheckInput;

import java.util.Scanner;

public class Controller {

    private final String GAME_TYPE = "game";
    private final String GAME_MODE = "mode";

    private Scanner scanner;

    public Controller(){

        scanner = new Scanner(System.in);
    }

    public int getGame(){

        System.out.println("-----------------------------");
        System.out.println("Sélectionner un jeu :");
        System.out.println("[1] - Mastermind");
        System.out.println("[2] - Recherche +/-");

        String GAME_MSG = "Saissir le numéro correspondant au jeu souhaité";
        return getInput(GAME_TYPE, GAME_MSG);
    }

    public int getMode(){

        System.out.println("-----------------------------");
        System.out.println("Sélectionner un mode de jeu :");
        System.out.println("[1] - Challenger");
        System.out.println("[2] - Duel");
        System.out.println("[3] - Défenseur");

        String MODE_MSG = "Saissir le numéro correspondant au mode de jeu souhaité";
        return getInput(GAME_MODE, MODE_MSG);

    }

    private int getInput(String type, String msg){

        int nbChoices = 0;
        int input;

        if(type.equals(GAME_TYPE)) nbChoices = 2;
        else if(type.equals(GAME_MODE)) nbChoices = 3;

        while (true){

            String line = scanner.nextLine();

            if (!CheckInput.config(line, nbChoices)) System.out.println(msg);
            else {
                input = Integer.parseInt(line);
                break;
            }
        }
        return input;
    }
}
