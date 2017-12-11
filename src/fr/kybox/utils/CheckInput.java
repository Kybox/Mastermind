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

    public static boolean isApproved(String s, int length){

        try{ parseInt(s); }
        catch (NumberFormatException | NullPointerException e) {
            System.out.println("Vous devez saisir une combinaison de chiffres !");
            return false;
        }

        if(s.length() < length || s.length() > length) {
            System.out.println("Vous devez saisir une combinaison de " + length + " chiffres !");
            return false;
        }
        else return true;
    }


}
