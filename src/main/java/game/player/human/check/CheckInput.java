package main.java.game.player.human.check;

import main.java.game.Game;
import main.java.utils.Settings;
import main.java.view.Display;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class CheckInput {

    private static final Logger LOG = LogManager.getLogger(CheckInput.class);

    /**
     * Check if the value entered by the user is correct
     * @param   selection       The selection
     * @param   maxSelection    The maximum value of selection
     * @return                  True if the selection is correct otherwise false
     */
    public static boolean menuSelection(String selection, int maxSelection){

        int mode;
        try{ mode = Integer.parseInt(selection); }
        catch (NumberFormatException | NullPointerException e) { return false; }
        return mode != 0 && mode < (maxSelection + 1);
    }

    /**
     * Check if the combination entered by the user is in the correct format
     * @param   combination     The combination entered by the user
     * @return                  True for a correct format otherwise false
     */
    public static boolean inputCombinationApproved(String combination){

        try{ Integer.parseInt(combination); }
        catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        int nbKeys = Settings.getKeys();
        if(combination.length() < nbKeys || combination.length() > nbKeys) return false;

        int maxNumbers = Settings.getMaxNumber();
        for(int i = 0; i < combination.length(); i++){
            if(Integer.parseInt(String.valueOf(combination.charAt(i))) > maxNumbers - 1)
                return false;
        }

        return true;
    }

    /**
     * Check if the clues are in the correct format
     * @param   clues           The clues
     * @param   combination     The new proposed combination
     * @param   secretCode      The secret combination
     * @return                  True if all is correct otherwise false
     */
    public static boolean checkCluesSyntax(String clues, String combination, int[] secretCode){

        switch (Game.GAME_TYPE) {
            // Mastermind
            case 1:
                if (clues.length() != 3 || clues.charAt(1) != ','
                        || !Character.isDigit(clues.charAt(0)) || !Character.isDigit(clues.charAt(2))) {
                    Display.invalidFormat();
                    return false;
                } else if (!checkClues(clues, combination, secretCode)) {
                    Display.invalidClues();
                    return false;
                }
                else return true;

            // Search +/-
            case 2:
                if (clues.length() != Settings.getKeys()) {
                    Display.invalidFormat();
                    return false;
                }
                else {
                    if (!checkClues(clues, combination, secretCode)) {
                        Display.invalidClues();
                        return false;
                    }
                    else return true;
                }

            default:
                LOG.error("Invalid game type");
                return false;
        }
    }

    /**
     * Check if the suggested clues are correct
     * @param   clues       The clues
     * @param   combination        The new proposed combination
     * @param   secretCode  The secret combination
     * @return              True if all is correct otherwise false
     */
    private static boolean checkClues(String clues, String combination, int[] secretCode){

        switch (Game.GAME_TYPE){
            case 1:
                int wellPut = 0;
                int existing = 0;
                for(int i = 0; i < combination.length(); i++){
                    int value = Integer.parseInt(String.valueOf(combination.charAt(i)));
                    if(value == secretCode[i]) wellPut++;
                    else if(Arrays.stream(secretCode).anyMatch(j -> j == value))
                        existing++;
                }
                String checked = wellPut + "," + existing;
                return checked.equals(clues);

            case 2:
                boolean error = false;
                for (int i = 0; i < clues.length(); i++) {
                    if (clues.charAt(i) != '-' && clues.charAt(i) != '=' && clues.charAt(i) != '+') {
                        Display.invalidFormat();
                        return false;
                    } else {
                        int nb = Integer.parseInt(String.valueOf(combination.charAt(i)));
                        if (nb < secretCode[i] && clues.charAt(i) != '+') error = true;
                        else if (nb > secretCode[i] && clues.charAt(i) != '-') error = true;
                        else if (nb == secretCode[i] && clues.charAt(i) != '=') error = true;
                        if (error) break;
                    }
                }

                return !error;

            default: return false;
        }
    }

    /**
     * Check if the user correctly entered the index of a selection
     * @param   reply   The reply
     * @return          True if all is correct otherwise false
     */
    public static boolean endgameMenuSelection(String reply){

        if(reply.equals("1") || reply.equals("2") || reply.equals("3")) return true;
        else {
            Display.invalidMenuSelection();
            return false;
        }
    }
}
