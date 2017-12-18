package main.java.game.player.ai.strategy;

import main.java.view.Display;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class Strategy {

    private ArrayList<int[]> combinationList;
    private Map<int[], Integer> existingList;
    private Map<int[], Integer> wellPutList;
    private int nbBoxes;
    private int maxNumber;
    private int loop = 0;
    private int total = 0;
    private int totalComb = 0;
    private int percent = 0;

    private static final Logger LOG = LogManager.getLogger(Strategy.class);

    public Strategy(){ }

    public void initCombinations(int nbBoxes, int maxNumber){

        this.nbBoxes = nbBoxes;
        this.maxNumber = maxNumber;

        totalComb =  (int) Math.pow(maxNumber, nbBoxes);
        combinationList = setCombinations();

        wellPutList = new HashMap<>();
        existingList = new HashMap<>();

        //Display.info(combinationList.size() + " total combinations parsed");
    }

    public void setClues(String clues, int[] code){

        int wellPut = Integer.parseInt(String.valueOf(clues.charAt(0)));
        int existing = Integer.parseInt(String.valueOf(clues.charAt(2)));

        if(wellPut == 0) combinationList = removeAllFor(code);
        else wellPutList.put(code, wellPut);

        if(existing == code.length) combinationList = removeNotContain(code);
        else if(existing + wellPut == code.length) combinationList = removeNotContain(code);
        else if(existing == 0) combinationList = removeAllFor(code);
        else existingList.put(code, existing);

        combinationList.remove(code);

        //Display.info( combinationList.size() + " combinaitions left");
    }

    public int[] getNewCode(){

        return combinationList.get(new Random().nextInt(combinationList.size()));
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

        //Display.info(uselessList.size() + " codes to remove");

        for(int[] value : uselessList) combinationList.remove(value);

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

        return combinationList;
    }

    private void checkWellPlaced(){


    }

    private ArrayList<int[]> setCombinations(){

        String numbers = stringOfNumbers(maxNumber);
        ArrayList<int[]> list = new ArrayList<>();

        if(nbBoxes == numbers.length()) list = getCombinations(numbers, list);
        else{
            int nextInt = 0;
            String strNext = "0";

            for(int j = 0; j < maxNumber; j++){

                String range = numbers.substring(0, nbBoxes);

                if(j != 0) {
                    if(j + nbBoxes <= numbers.length()){
                        range = numbers.substring(j, nbBoxes + j);
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


        LOG.info(list.size() + " combinations combinations ");
        Display.info("");

        return list;
    }

    /**
     * Return a ArrayList<int[]> with all permuted characters from a String to Integer array
     * <p>
     *     If range equals to "123" the ArrayList contains then the ArrayList contains :
     *     [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]
     * </p>
     * @param   range   The String to swap
     * @param   list    The ArrayList<int[]> in which to add the permutations
     * @return          The ArrayList in parameter with new permutations
     */
    private ArrayList<int[]> getCombinations(String range, ArrayList<int[]> list){

        int [] factorials = new int[nbBoxes + 1];

        factorials[0] = 1;

        for (int i = 1; i <= nbBoxes; i++) {
            factorials[i] = factorials[i-1] * i;
        }

        for (int i = 0; i < factorials[nbBoxes]; i++) {

            String newCode="";
            String temp = range;

            int index = i;

            for (int j = nbBoxes; j > 0 ;j--){

                int selected = index / factorials[j-1];

                newCode += temp.charAt(selected);
                index = index % factorials[j-1];
                temp = temp.substring(0,selected) + temp.substring(selected+1);
            }

            int[]code = new int[nbBoxes];
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
