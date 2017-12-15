package main.java.utils;

import main.java.game.Game;
import main.java.view.Display;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class CheckInput {

    public static boolean params(String s, int max){

        try{ parseInt(s); }
        catch (NumberFormatException | NullPointerException e) { return false; }

        int mode = parseInt(s);

        return mode != 0 && mode < (max + 1);
    }

    public static boolean isApproved(String s, int nbBoxes, int maxNumbers){

        try{ parseInt(s); }
        catch (NumberFormatException | NullPointerException e) {
            Display.info("Vous devez saisir une combinaison de chiffres !");
            return false;
        }

        if(s.length() < nbBoxes || s.length() > nbBoxes) {
            Display.info("Vous devez saisir une combinaison de " + nbBoxes + " chiffres !");
            return false;
        }

        for(int i = 0; i < s.length(); i++){
            if(Integer.parseInt(String.valueOf(s.charAt(i))) > maxNumbers - 1) {
                Display.info("Vous devez saisir des chiffres inférieurs ou égals à " + (maxNumbers - 1));
                return false;
            }
        }

        return true;
    }

    public static boolean getCluesGame1(String s, String code, int[] secretCode){

        if(s.length() != 3 || s.charAt(1) != ','
                || !Character.isDigit(s.charAt(0)) || !Character.isDigit(s.charAt(2))){
            Display.error("Vous devez saisir 2 indices séparés par une virgule :");
            Display.info("\tle premier pour le nombre de chiffre bien placés,");
            Display.info("\tet le second pour le nombre de chiffres présents mal placés");
            Display.info("\tExemple : 1 bien placé & 2 présents donne -> 1,2");
            return false;
        }
        else if(!checkClues(s, code, secretCode)){
            Display.error("\tVotre indice n'est pas correct !");
            Display.error("\tMerci de vérifier votre réponse.");
            return false;
        }
        else return true;
    }
    public static boolean getCluesGame2(String s, String code, int[] secretCode){

        if(s.length() != Settings.getBoxes()) {
            Display.error("Vous devez saisir un indice pour chacun des " + Settings.getBoxes() + " chiffres !");
            return false;
        }
        else {
            boolean error = false;
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) != '-' && s.charAt(i) != '=' && s.charAt(i) != '+') {
                    Display.error("Vous devez saisir un indice (- ou = ou +) pour chaque chiffre");
                    Display.error("\tExemple : +--=");
                    return false;
                }
                else{
                    int nb = Integer.parseInt(String.valueOf(code.charAt(i)));
                    if(nb < secretCode[i] && s.charAt(i) != '+') error = true;
                    else if(nb > secretCode[i] &&  s.charAt(i) != '-') error = true;
                    else if(nb == secretCode[i] && s.charAt(i) != '=') error = true;
                    if(error) break;
                }
            }

            if(error){
                Display.error("\tVotre indice n'est pas correct !");
                Display.error("\tMerci de vérifier votre réponse.");
                return false;
            }
            else return true;
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

    public static boolean checkReply(String reply){

        if(reply.equals("1") || reply.equals("2") || reply.equals("3")) return true;
        else {
            Display.error("Veuillez saisir 1, 2 ou 3 suivant votre choix de réponse");
            return false;
        }
    }
}
