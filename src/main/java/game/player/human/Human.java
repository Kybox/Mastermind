package main.java.game.player.human;

import main.java.utils.CheckInput;
import main.java.view.Display;

import java.util.Scanner;

public class Human {

    private final String GAME_TYPE = "game";
    private final String GAME_MODE = "mode";

    private Scanner scanner;

    public Human(){
        scanner = new Scanner(System.in);
    }

    public int getGame(){

        Display.mainMenuSelections();
        String gameMsg = "Saissir le numéro correspondant au jeu souhaité";
        return getParamsInput(GAME_TYPE, gameMsg);
    }

    public int getMode(){

        Display.gameModeSelections();
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

            if (!CheckInput.menuSelection(line, nbChoices)) Display.error(msg);
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

            if(!CheckInput.inputCombinationApproved(line)) {
                Display.invalidCombination();
                continue;
            }
            else break;
        }

        return line;
    }

    public String getSecretCode(){

        Display.secretCombinationSelection();

        String line;

        while (true){

            line = scanner.nextLine();

            if(!CheckInput.inputCombinationApproved(line)){
                Display.invalidCombination();
                continue;
            }
            else break;
        }

        return line;
    }

    public String getClues(String code, int[] secretCode){

        String line;

        while (true){
            line = scanner.nextLine();
            if(!CheckInput.checkCluesSyntax(line, code, secretCode)) continue;
            else break;
        }

        return line;
    }

    public int getMenuSelection(){

        int reply;

        while (true){

            String line = scanner.nextLine();

            if(!CheckInput.gameOverSelection(line)) continue;
            else {
                reply = Integer.parseInt(line);
                break;
            }
        }

        return reply;
    }
}
