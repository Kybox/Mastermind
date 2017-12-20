package main.java.game.player.human;

import main.java.utils.CheckInput;
import main.java.view.Display;

import java.util.Scanner;

public class Human {

    /**
     * Default Human constructor
     */
    public Human(){ }

    /**
     * Get the desired game
     * @return  The index of the selected game
     */
    public int getGame(){

        Display.mainMenuSelections();
        String gameMsg = "Saissir le numéro correspondant au jeu souhaité";
        return getParamsInput(2, gameMsg);
    }

    /**
     * Get the desired game mode
     * @return  The index of the selected game mode
     */
    public int getMode(){

        Display.gameModeSelections();
        String modeMsg = "Saissir le numéro correspondant au mode de jeu souhaité";
        return getParamsInput(3, modeMsg);
    }

    /**
     * Get player input for the game and the game mode
     * @param   maxIndex    The maximum index that can be selected
     * @param   msg         The error message if the value is incorrect
     * @return              The index of the selected choice
     */
    private int getParamsInput(int maxIndex, String msg){

        int input;

        while (true){

            String line = new Scanner(System.in).nextLine();
            if (!CheckInput.menuSelection(line, maxIndex)) Display.error(msg);
            else {
                input = Integer.parseInt(line);
                break;
            }
        }
        return input;
    }

    /**
     * Get the combination entered by the player
     * @return  The player's combination
     */
    public String getGameInput(){

        String line;

        while (true){

            line = new Scanner(System.in).nextLine();
            if(!CheckInput.inputCombinationApproved(line)) Display.invalidCombination();
            else break;
        }

        return line;
    }

    /**
     * Get the secret combination created by the player
     * @return  The secret combination
     */
    public String getSecretCode(){

        Display.secretCombinationSelection();
        return getGameInput();
    }

    /**
     * Get the clues provided by the player
     * @param code          The player's combination
     * @param secretCode    The secret combination
     * @return              The clues
     */
    public String getClues(String code, int[] secretCode){

        String line;

        while (true){
            line = new Scanner(System.in).nextLine();
            if(!CheckInput.checkCluesSyntax(line, code, secretCode)) continue;
            else break;
        }

        return line;
    }

    /**
     * Get the game over menu selection
     * @return  The choice of the player
     */
    public int getGameOverSelection(){

        int reply;

        while (true){

            String line = new Scanner(System.in).nextLine();
            if(!CheckInput.gameOverSelection(line)) continue;
            else {
                reply = Integer.parseInt(line);
                break;
            }
        }

        return reply;
    }
}
