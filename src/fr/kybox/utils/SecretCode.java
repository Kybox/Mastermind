package fr.kybox.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public class SecretCode {

    private static final Logger LOG = LogManager.getLogger(SecretCode.class);

    public static int[] generate(int boxes, int nbNumbers){

        int index = 0;
        Random random = new Random();
        int[] keys = new int[boxes];

        while (index < boxes){

            int key = random.nextInt(nbNumbers);

            keys[index] = key;
            index++;
        }

        return keys;
    }

    public static boolean isEqual(int[] secretCode, String userCode){

        int[] code = convertToDigit(userCode);

        if(Arrays.equals(secretCode, code)) return true;
        else return false;
    }

    public static int[] convertToDigit(String code){

        int[] result = new int[code.length()];

        for(int i = 0; i < code.length(); i++){
            try{ result[i] = Integer.parseInt(String.valueOf(code.charAt(i))); }
            catch (NumberFormatException e ) { LOG.fatal("Invalid code to digit conversion"); }
        }
        return result;
    }

    public static String getClues(int game, int[] secretCode, String userCode){

        String clues = "";
        int[] code = convertToDigit(userCode);

        switch (game){

            case 1:
                int existing = 0;
                int wellPut = 0;


                for(int i = 0; i < secretCode.length; i++){

                    for(int j = 0; j < secretCode.length; j++){

                        if(code[i] == secretCode[j]) existing++;
                    }
                    if(code[i] == secretCode[i]) wellPut++;
                }

                String strExisting;
                if(existing > 1) strExisting = existing + " présents, ";
                else strExisting = existing + " présent, ";

                String strWellPut;
                if(wellPut > 1) strWellPut = wellPut + " bien placés.";
                else strWellPut = wellPut + " bien placé.";

                clues = strExisting + strWellPut;
                break;

            case 2:

                for(int i = 0; i < secretCode.length; i++){

                    if(secretCode[i] < code[i]) clues = clues + "-";
                    else if(secretCode[i] == code[i]) clues = clues + "=";
                    else if(secretCode[i] > code[i]) clues = clues + "+";
                }

                break;

            default:
                LOG.fatal("Incorrect game type");
        }

        return clues;
    }
}
