package main.java.utils;

import main.java.game.Game;
import main.java.view.Display;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class CheckInput {

    private static final Logger LOG = LogManager.getLogger(CheckInput.class);

    public static boolean params(String s, int max){

        int mode;
        try{ mode = Integer.parseInt(s); }
        catch (NumberFormatException | NullPointerException e) { return false; }

        return mode != 0 && mode < (max + 1);
    }

    public static boolean isApproved(String s){

        try{ Integer.parseInt(s); }
        catch (NumberFormatException | NullPointerException e) {
            Display.info("Vous devez saisir une combinaison de chiffres !");
            return false;
        }

        int nbBoxes = Settings.getBoxes();
        if(s.length() < nbBoxes || s.length() > nbBoxes) {
            Display.info("Vous devez saisir une combinaison de " + nbBoxes + " chiffres !");
            return false;
        }

        int maxNumbers = Settings.getMaxNumbers();
        for(int i = 0; i < s.length(); i++){
            if(Integer.parseInt(String.valueOf(s.charAt(i))) > maxNumbers - 1) {
                Display.info("Vous devez saisir des chiffres inférieurs ou égals à " + (maxNumbers - 1));
                return false;
            }
        }

        return true;
    }

    public static boolean getClues(String s, String code, int[] secretCode){
        switch (Game.GAME_TYPE) {
            // Mastermind
            case 1:
                if (s.length() != 3 || s.charAt(1) != ','
                        || !Character.isDigit(s.charAt(0)) || !Character.isDigit(s.charAt(2))) {
                    Display.invalidFormat();
                    return false;
                } else if (!checkClues(s, code, secretCode)) {
                    Display.invalidClues();
                    return false;
                }
                else return true;

            // Search +/-
            case 2:
                if (s.length() != Settings.getBoxes()) {
                    Display.invalidFormat();
                    return false;
                }
                else {
                    boolean error = false;
                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) != '-' && s.charAt(i) != '=' && s.charAt(i) != '+') {
                            Display.invalidFormat();
                            return false;
                        } else {
                            int nb = Integer.parseInt(String.valueOf(code.charAt(i)));
                            if (nb < secretCode[i] && s.charAt(i) != '+') error = true;
                            else if (nb > secretCode[i] && s.charAt(i) != '-') error = true;
                            else if (nb == secretCode[i] && s.charAt(i) != '=') error = true;
                            if (error) break;
                        }
                    }

                    if (error) {
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

    public static boolean checkClues(String clues, String code, int[] secretCode){

        switch (Game.GAME_TYPE){
            case 1:
                int wellPut = 0;
                int existing = 0;
                for(int i = 0; i < code.length(); i++){
                    int value = Integer.parseInt(String.valueOf(code.charAt(i)));
                    if(value == secretCode[i]) wellPut++;
                    else if(Arrays.stream(secretCode).anyMatch(j -> j == value))
                        existing++;
                }
                String checked = wellPut + "," + existing;
                if(checked.equals(clues)) return true;
                else return false;

            case 2:
                return false;

            default: return false;
        }
    }

    public static boolean checkMenuSelection(String reply){

        if(reply.equals("1") || reply.equals("2") || reply.equals("3")) return true;
        else {
            Display.invalidMenuSelection();
            return false;
        }
    }
}
