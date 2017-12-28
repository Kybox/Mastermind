package main.java.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public class SecretCode {

    private static final Logger LOG = LogManager.getLogger(SecretCode.class);

    /**
     * Generate a secret combination based on the method's parameters
     * @param   nbKeys      Number of key elements in the combination
     * @param   maxNumbers  Maximum value of the numbers that make up the combination
     * @return              A secret combination
     */
    public static int[] generate(int nbKeys, int maxNumbers){

        int index = 0, key;
        Random random = new Random();
        int[] code = new int[nbKeys];

        while (index < nbKeys){

            boolean exist;

            do{
                exist = false;
                key = random.nextInt(maxNumbers);

                for (int i = 0; i < index; i++) {
                    if (code[i] == key) {
                        exist = true;
                        break;
                    }
                }
            }
            while (exist);

            code[index] = key;
            index++;
        }
        return code;
    }

    /**
     * Checking if the user's combination is identical to the secret combination
     * @param   secretCode  The secret combination to find
     * @param   userCode    The combination that the user proposed
     * @return              True if both combinaitions are identical otherwise return false
     */
    public static boolean isEqual(int[] secretCode, String userCode){

        int[] code = convertToDigit(userCode);

        return Arrays.equals(secretCode, code);
    }

    /**
     * Convert a string into an array of integers
     * @param   code    A combination in a string
     * @return          An array of integers
     */
    public static int[] convertToDigit(String code){

        int[] array = new int[code.length()];

        for(int i = 0; i < code.length(); i++){
            try{ array[i] = Integer.parseInt(String.valueOf(code.charAt(i))); }
            catch (NumberFormatException e ) { LOG.fatal("Invalid code to digit conversion"); }
        }
        return array;
    }
}
