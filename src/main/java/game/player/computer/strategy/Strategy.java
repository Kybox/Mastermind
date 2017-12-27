package main.java.game.player.computer.strategy;

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

    /**
     * Parse clues and remove combinations based on clues
     * @param   clues   Number of well placed and misplaced
     * @param   code    The last combination generated
     */
    public void setClues(String clues, int[] code){

        int nbWellPlaced = Integer.parseInt(String.valueOf(clues.charAt(0)));
        int nbMisplaced = Integer.parseInt(String.valueOf(clues.charAt(2)));

        if(nbWellPlaced == 0) combinationList = removeAllFor(code);

        if(nbMisplaced == code.length) combinationList = removeNotContain(code);
        else if(nbMisplaced + nbWellPlaced == code.length) combinationList = removeNotContain(code);

        combinationList.remove(code);
    }

    /**
     * Get a new combination from the list
     * @return  A new combination
     */
    public int[] getNewCode(){

        if(combinationList.size() > 1)
            return combinationList.get(new Random().nextInt(combinationList.size()));
        else return combinationList.get(0);
    }

    /**
     * Removes all combinations that do not contain the elements of the proposed combination
     * @param   combination    The last combination generated
     * @return                  The new combination list
     */
    private ArrayList<int[]> removeNotContain(int[] combination){

        Iterator<int[]> iterator = combinationList.iterator();

        while (iterator.hasNext()){

            int[] code = iterator.next();

            for(int key : combination){
                if(!Arrays.stream(code).anyMatch(e -> e == key)) {
                    LOG.info("Removed combination -> " + Arrays.toString(code));
                    iterator.remove();
                    break;
                }
            }
        }

        return combinationList;
    }

    /**
     * Remove all combinations that contain the keys of the last combination generated
     * @param   combination     The last combination generated
     * @return                  The new combination list
     */
    private ArrayList<int[]> removeAllFor(int[] combination){

        Iterator<int[]> iterator = combinationList.iterator();

        while (iterator.hasNext()){
            int[] code = iterator.next();
            for(int i = 0; i < code.length; i++){
                if(code[i] == combination[i]) {
                    LOG.info("Removed combination -> " + Arrays.toString(code));
                    iterator.remove();
                    break;
                }
            }
        }

        return combinationList;
    }

    /**
     * Generate all combinations with all possible permutations
     * @return  The list of all combinations
     */
    private ArrayList<int[]> setCombinations(){

        String numbers = stringOfNumbers(maxNumber);
        ArrayList<int[]> list = new ArrayList<>();

        if(nbKeys == numbers.length()) list = getCombinations(stringToArray(numbers), list);
        else{
            int nextInt = 0;
            StringBuilder strNext = new StringBuilder("0");

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
                        strNext.append(nextInt);
                    }
                }
                list = getCombinations(stringToArray(range), list);
            }
        }

        return list;
    }

    /**
     * Swap integers in the array passed as a parameter
     * @param   range               An integer array
     * @param   combinationList     The integers array that will be return with combinations
     * @return                      The list of all possible permutations of range
     */
    public ArrayList<int[]> getCombinations(int[] range, ArrayList<int[]> combinationList) {

        // Main list that will be incremented
        ArrayList<List<Integer>> storageList = new ArrayList<>();

        // Adding a first list to start the iteration
        storageList.add(new ArrayList<>());

        // Iteration over each integer in range[]
        for (int i = 0; i < range.length; i++) {

            System.out.println("--------------------------------");
            System.out.println("Main Iteration = " + (i+1));

            // Temporary list that will be added to the main list
            ArrayList<List<Integer>> tempStorageList = new ArrayList<>();

            System.out.println("StorageList size = " + storageList.size());

            int nbList = 1;

            // Iteration on each list of storageList
            for (List<Integer> list : storageList) {

                System.out.println("---------");
                System.out.println("Iteration list = " + nbList + " / " + (list.size() + 1));

                // Iteration on each integer in the list
                for (int j = 0; j <= list.size(); j++) {

                    // Add a new integer (range[x]) at the index j
                    list.add(j, range[i]);

                    System.out.println("-> Add " + range[i] + " at index " +  j);

                    System.out.println("list = " + list.toString());


                    List<Integer> tempCombList = new ArrayList<>(list);

                    System.out.println("tempCombList = " + tempCombList.toString());
                    list.remove(j);
                    System.out.println("list = " + list.toString());

                    tempStorageList.add(tempCombList);

                    System.out.println("Currentset = " + tempStorageList.toString());
                }

                nbList++;
            }

            System.out.println("Final Currentset = " + tempStorageList.toString());

            storageList = new ArrayList<>(tempStorageList);
        }

        for(List<Integer> integerList : storageList)
            combinationList.add(integerList.stream().mapToInt(e -> e).toArray());

        return combinationList;
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

        StringBuilder stringOfNumbers = new StringBuilder();
        for(int i = 0; i < value; i++) stringOfNumbers.append(i);

        return stringOfNumbers.toString();
    }

    private int[] stringToArray(String value){

        int[] intArray = new int[value.length()];

        for (int i = 0; i < value.length(); i++) {
            intArray[i] = Character.digit(value.charAt(i), 10);
        }

        return intArray;
    }
}
