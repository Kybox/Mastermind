package fr.kybox.utils;

import static java.lang.Integer.parseInt;

public class CheckInput {

    public static boolean config(String s, int max){

        try{ parseInt(s); }
        catch (NumberFormatException | NullPointerException e) { return false; }

        int mode = parseInt(s);

        return mode != 0 && mode < (max + 1);
    }
}
