package main.java.game.player.computer.strategy;

import main.java.game.Game;
import main.java.utils.Convert;
import main.java.utils.Settings;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class Strategy {

    private ArrayList<int[]> combinationList;
    private final int nbKeys;
    private final int maxNumber;
    private int[] maxKeys;
    private int[] minKeys;
    private int[] newCode;

    private static final Logger LOG = LogManager.getLogger(Strategy.class);

    public Strategy(){
        this.nbKeys = Settings.getKeys();
        this.maxNumber = Settings.getMaxNumber();

        if(Game.GAME_TYPE == 1) combinationList = setCombinations();
    }

    /**
     * Parse clues and keep combinations based on clues
     * @param   clues   Number of well placed and misplaced
     * @param   code    The last combination generated
     */
    public void setClues(String clues, int[] code){

        switch (Game.GAME_TYPE){

            // Mastermind
            case 1:
                int nbWellPlaced = Integer.parseInt(String.valueOf(clues.charAt(0)));
                int nbMisplaced = Integer.parseInt(String.valueOf(clues.charAt(2)));

                if(nbWellPlaced == 0) combinationList = removeAllFor(code);

                if(nbMisplaced == code.length) combinationList = removeNotContain(code);
                else if(nbMisplaced + nbWellPlaced == code.length) combinationList = removeNotContain(code);

                combinationList.remove(code);

                break;

            // Search +/-
            case 2:

                if(maxKeys == null) maxKeys = new int[nbKeys];
                if(minKeys == null) minKeys = new int[nbKeys];
                if(newCode == null) newCode = new int[nbKeys];

                Random random = new Random();

                for(int i = 0; i < nbKeys; i++){

                    switch (clues.charAt(i)) {

                        case '-':
                            maxKeys[i] = code[i] - 1;
                            if (maxKeys[i] != 0) newCode[i] = random.nextInt(maxKeys[i]);
                            else newCode[i] = 0;
                            break;

                        case '+':
                            minKeys[i] = code[i] + 1;
                            newCode[i] = random.nextInt(maxNumber - minKeys[i]) + minKeys[i];
                            break;
                        default:
                            newCode[i] = code[i];
                            break;
                    }
                }
                break;
        }
    }

    /**
     * Get a new combination from the list
     * @return  A new combination
     */
    public int[] getNewCode(){

        switch (Game.GAME_TYPE){
            case 1:
                if(combinationList.size() > 1)
                    return combinationList.get(new Random().nextInt(combinationList.size()));
                else return combinationList.get(0);

            case 2: return newCode;

            default:
                LOG.error("Invalid game type");
                return null;
        }
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
                if(Arrays.stream(code).noneMatch(e -> e == key)) {
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

        String numbers = Convert.integerToStringRange(maxNumber);
        ArrayList<int[]> list = new ArrayList<>();

        if(nbKeys == numbers.length()) list = getCombinations(Convert.stringToArray(numbers), list);
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
                list = getCombinations(Convert.stringToArray(range), list);
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
    private ArrayList<int[]> getCombinations(int[] range, ArrayList<int[]> combinationList) {

        // Main list that will be incremented
        ArrayList<List<Integer>> storageList = new ArrayList<>();

        // Adding a first list to start the iteration
        storageList.add(new ArrayList<>());

        // Iteration over each integer in range[]
        for (int key : range) {

            // Temporary list that will be added to the main list
            ArrayList<List<Integer>> tempStorageList = new ArrayList<>();

            // Iteration on each list of storageList
            for (List<Integer> list : storageList) {

                // Iteration on each integer in the list
                for (int j = 0; j <= list.size(); j++) {

                    // Add a new integer (range[x]) at the index j
                    list.add(j, key);

                    List<Integer> tempCombList = new ArrayList<>(list);

                    list.remove(j);

                    tempStorageList.add(tempCombList);
                }
            }

            storageList = new ArrayList<>(tempStorageList);
        }

        for(List<Integer> integerList : storageList)
            combinationList.add(integerList.stream().mapToInt(e -> e).toArray());

        return combinationList;
    }
}
