package main.java.game.player.ai.strategy;

import main.java.view.Display;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class Strategy {

    private ArrayList<int[]> combinationList;
    private int nbKeys;
    private int maxNumber;

    private static final Logger LOG = LogManager.getLogger(Strategy.class);

    public Strategy(){ }

    /**
     * Initialization of the strategy by all combinations
     * @param   nbKeys      The number of keys in the combination
     * @param   maxNumber   The maximum number in the combination
     */
    public void initCombinations(int nbKeys, int maxNumber){

        this.nbKeys = nbKeys;
        this.maxNumber = maxNumber;

        combinationList = setCombinations();
    }


    public void setClues(String clues, int[] code){

        int wellPut = Integer.parseInt(String.valueOf(clues.charAt(0)));
        int nbMisplaced = Integer.parseInt(String.valueOf(clues.charAt(2)));

        if(wellPut == 0) combinationList = removeAllFor(code);

        if(nbMisplaced == code.length) combinationList = removeNotContain(code);
        else if(nbMisplaced + wellPut == code.length) combinationList = removeNotContain(code);

        combinationList.remove(code);

        LOG.info( combinationList.size() + " combinaitions left");
    }

    public int[] getNewCode(){

        if(combinationList.size() > 1)
            return combinationList.get(new Random().nextInt(combinationList.size()));
        else return combinationList.get(0);
    }

    private ArrayList<int[]> removeNotContain(int[]code){

        ArrayList<int[]> uselessList = new ArrayList<>();
        ArrayList<Integer> uselessNumber = new ArrayList<>();

        for(int i = 0; i < maxNumber; i++){
            final int nb = i;
            if(!Arrays.stream(code).anyMatch(j -> j == nb))
                uselessNumber.add(nb);
        }

        for(int[] value : combinationList){
            for(int uselessNb : uselessNumber){
                if(Arrays.stream(value).anyMatch(i -> i == uselessNb))
                    uselessList.add(value);
            }
        }

        LOG.info(uselessList.size() + " codes to remove");

        for(int[] value : uselessList)
            combinationList.remove(value);

        return combinationList;
    }

    private ArrayList<int[]> removeAllFor(int[] code){

        ArrayList<int[]> uselessList = new ArrayList<>();

        for(int[] value : combinationList){
            for(int i = 0; i < value.length; i++){
                if(value[i] == code[i]) uselessList.add(value);
            }
        }

        for(int[] value : uselessList) combinationList.remove(value);

        LOG.info(uselessList.size() + " codes to remove");

        return combinationList;
    }

    private ArrayList<int[]> setCombinations(){

        String numbers = stringOfNumbers(maxNumber);
        ArrayList<int[]> list = new ArrayList<>();

        if(nbKeys == numbers.length()) list = getCombinations(numbers, list);
        else{
            int nextInt = 0;
            String strNext = "0";

            for(int j = 0; j < maxNumber; j++){

                String range = numbers.substring(0, nbKeys);

                if(j != 0) {
                    if(j + nbKeys <= numbers.length()){
                        range = numbers.substring(j, nbKeys + j);
                    }
                    else{
                        range = numbers.substring(j, numbers.length());
                        range = range + strNext;
                        nextInt++;
                        strNext = strNext + nextInt;
                    }
                }
                list = getCombinations(range, list);
            }
        }


        LOG.info(list.size() + " combinations generated");
        Display.info("");

        return list;
    }

    /**
     * Return a ArrayList<int[]> with all permuted characters from a String to Integer array
     * <p>
     *     If range equals to "123" then the ArrayList contains :
     *     [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]
     * </p>
     * @param   range   The String to swap
     * @param   list    The ArrayList<int[]> in which to add the permutations
     * @return          The ArrayList in parameter with new permutations
     */
    private ArrayList<int[]> getCombinations(String range, ArrayList<int[]> list){

        int [] factorials = new int[nbKeys + 1];

        factorials[0] = 1;

        for (int i = 1; i <= nbKeys; i++) {
            factorials[i] = factorials[i-1] * i;
        }

        for (int i = 0; i < factorials[nbKeys]; i++) {

            String newCode="";
            String temp = range;

            int index = i;

            for (int j = nbKeys; j > 0 ; j--){

                int selected = index / factorials[j-1];

                newCode += temp.charAt(selected);
                index = index % factorials[j-1];
                temp = temp.substring(0,selected) + temp.substring(selected+1);
            }

            int[]code = new int[nbKeys];
            for(int j = 0; j < newCode.length(); j++){
                if(!Character.isDigit(newCode.charAt(j))) {
                    LOG.error("Error when converting char to int");
                    break;
                }
                code[j] = Integer.parseInt(String.valueOf(newCode.charAt(j)));
            }

            list.add(code);
        }

        return list;
    }

    /**
     * Return a String of numbers
     * <p>
     *     If value = 5 then the String equals to "01234"
     * </p>
     * @param   value   The maximum value starting from zero
     * @return          A String with all numbers to the value
     */
    private String stringOfNumbers(int value){

        String stringOfNumbers = "";
        for(int i = 0; i < value; i++) stringOfNumbers = stringOfNumbers + i;

        return stringOfNumbers;
    }
}
