package main.java.game.player.human;

import main.java.game.player.Player;
import main.java.game.player.human.check.CheckInput;
import main.java.utils.SecretCode;
import main.java.view.Display;

import java.util.Scanner;

public class Human implements Player {

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
    public String getNewCombination(){

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
    @Override
    public int[] setSecretCode(){

        Display.secretCombinationSelection();
        return SecretCode.convertToDigit(getNewCombination());
    }

    /**
     * Get the clues provided by the player
     * @param combination   The player's combination
     * @param secretCode    The secret combination
     * @return              The clues
     */
    @Override
    public String getClues(String combination, int[] secretCode){

        String line;

        while (true){
            line = new Scanner(System.in).nextLine();
            if(!CheckInput.checkCluesSyntax(line, combination, secretCode)) continue;
            else break;
        }

        return line;
    }

    @Override
    public void setClues(String clues) {

    }

    /**
     * Get the game over menu selection
     * @return  The choice of the player
     */
    public int getEndgameSelection(){

        int reply;

        while (true){

            String line = new Scanner(System.in).nextLine();
            if(!CheckInput.endgameMenuSelection(line)) continue;
            else {
                reply = Integer.parseInt(line);
                break;
            }
        }

        return reply;
    }
}
