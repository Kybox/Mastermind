package main.java.utils;

public class Convert {

    /**
     * Convert an integer to a string of its range of values
     * If value = 5 then the String equals to "01234"
     * @param   value   The maximum value starting from zero
     * @return          A String with all numbers to the value
     */
    public static String integerToStringRange(int value){

        StringBuilder stringOfNumbers = new StringBuilder();
        for(int i = 0; i < value; i++) stringOfNumbers.append(i);

        return stringOfNumbers.toString();
    }

    /**
     * Convert a string value of intergers to an array in which each characters will be inserted
     * @param value The string value
     * @return      An array of integers
     */
    public static int[] stringToArray(String value){

        int[] intArray = new int[value.length()];

        for (int i = 0; i < value.length(); i++) {
            intArray[i] = Character.digit(value.charAt(i), 10);
        }

        return intArray;
    }
}
