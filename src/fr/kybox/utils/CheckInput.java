package fr.kybox.utils;

import static java.lang.Integer.parseInt;

public class CheckInput {

    public static boolean params(String s, int max){

        try{ parseInt(s); }
        catch (NumberFormatException | NullPointerException e) { return false; }

        int mode = parseInt(s);

        return mode != 0 && mode < (max + 1);
    }

    public static int getInteger(String s){
        return parseInt(s);
    }

    public static boolean isApproved(String s, int nbBoxes, int maxNumbers){

        try{ parseInt(s); }
        catch (NumberFormatException | NullPointerException e) {
            System.out.println("Vous devez saisir une combinaison de chiffres !");
            return false;
        }

        if(s.length() < nbBoxes || s.length() > nbBoxes) {
            System.out.println("Vous devez saisir une combinaison de " + nbBoxes + " chiffres !");
            return false;
        }

        for(int i = 0; i < s.length(); i++){
            if(Integer.parseInt(String.valueOf(s.charAt(i))) > maxNumbers - 1) {
                System.out.println("Vous devez saisir des chiffres inférieurs ou égals à " + (maxNumbers - 1));
                return false;
            }
        }

        return true;
    }

    public static boolean checkCluesGame2(String s, int nbBoxes){

        if(s.length() != nbBoxes) {
            System.out.println("Vous devez saisir un indice pour chacun des " + nbBoxes + " chiffres !");
            return false;
        }
        else {
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) != '-' && s.charAt(i) != '=' && s.charAt(i) != '+') {
                    System.out.println("Vous devez saisir un indice (- ou = ou +) pour chaque chiffre");
                    System.out.println("Exemple : +--=");
                    return false;
                }
            }

            return true;
        }
    }
}
