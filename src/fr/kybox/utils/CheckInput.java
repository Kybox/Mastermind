package fr.kybox.utils;

import java.util.Scanner;

public class CheckInput {

    public static boolean gameMode(String s){
        try{ Integer.parseInt(s); }
        catch (NumberFormatException e) { return false; }
        catch (NullPointerException e) { return false; }

        int mode = Integer.parseInt(s);

        if(mode != 0 && mode < 4) return true;
        else return false;
    }
}
