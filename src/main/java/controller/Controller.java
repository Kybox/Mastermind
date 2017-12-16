package main.java.controller;

import main.java.game.Game;
import main.java.utils.CheckInput;
import main.java.view.Display;

import java.util.Scanner;

public class Controller {

    private final String GAME_TYPE = "game";
    private final String GAME_MODE = "mode";

    private Scanner scanner;

    public Controller(){

        scanner = new Scanner(System.in);
    }

    public int getGame(){

        Display.info("Sélectionner un jeu :");
        Display.info("---------------------");
        Display.info("[1] - Mastermind");
        Display.info("[2] - Recherche +/-");

        String gameMsg = "Saissir le numéro correspondant au jeu souhaité";
        return getParamsInput(GAME_TYPE, gameMsg);
    }

    public int getMode(){

        Display.info("");
        Display.info("Sélectionner un mode de jeu :");
        Display.info("-----------------------------");
        Display.info("[1] - Challenger -> vous devez trouver la combinaison secrète de l'ordinateur");
        Display.info("[2] - Duel -> l'ordinateur et vous jouez tour à tour pour trouver la combinaison secrète");
        Display.info("[3] - Défenseur -> c'est à l'ordinateur de trouver votre combinaison secrète");

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

    public String getGameInput(){

        String line;

        while (true){

            line = scanner.nextLine();

            if(!CheckInput.isApproved(line)) continue;
            else break;
        }

        return line;
    }

    public String getSecretCode(int nbBoxes, int maxNumbers){

        Display.info("-----------------------------");
        Display.info("Sélectionner votre combinaison secrête :");
        Display.info("(" + nbBoxes + " chiffres compris entre 0 et " + (maxNumbers - 1) + ")");

        String line;

        while (true){

            line = scanner.nextLine();

            if(!CheckInput.isApproved(line)) continue;
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
                    if(!CheckInput.getCluesGame2(line, code, secretCode)) continue;
                    else break;
                }
                break;
        }

        return line;
    }

    public int getUserReply(){

        int reply;

        while (true){

            String line = scanner.nextLine();

            if(!CheckInput.checkReply(line)) continue;
            else {
                reply = Integer.parseInt(line);
                break;
            }
        }

        return reply;
    }
}
