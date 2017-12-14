package fr.kybox.game.player.ai.strategy;

import fr.kybox.view.Display;

import java.util.*;

public class Strategy {

    private ArrayList<int[]> combinationList;
    private Map<int[], Integer> existingList;
    private Map<int[], Integer> wellPutList;
    private int nbBoxes;
    private int maxNumber;

    public Strategy(){ }

    public void initCombinations(int nbBoxes, int maxNumber){
        this.nbBoxes = nbBoxes;
        this.maxNumber = maxNumber;
        combinationList = setCombinations();
        wellPutList = new HashMap<>();
        existingList = new HashMap<>();
        Display.info(combinationList.size() + " total combinations parsed");
        diplayCombinations();

    }

    public void setClues(String clues, int[] code){

        int wellPut = Integer.parseInt(String.valueOf(clues.charAt(0)));
        int existing = Integer.parseInt(String.valueOf(clues.charAt(2)));

        if(wellPut == 0) combinationList = removeAllFor(code);
        else wellPutList.put(code, wellPut);

        if(existing == code.length) combinationList = removeNotContain(code);
        else if(existing == 0) combinationList = removeAllFor(code);
        else existingList.put(code, existing);

        combinationList.remove(code);

        Display.info( combinationList.size() + " combinaitions left");
        diplayCombinations();
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

        Display.info(uselessList.size() + " codes to remove");

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

        char[] nbList = new char[maxNumber];

        for(int i = 0; i < maxNumber; i++)
            nbList[i] = Integer.toString(i).charAt(0);

        ArrayList<int[]> list = new ArrayList<>();

        getCombinations(nbList, "", nbList.length, nbBoxes, list);

        Display.info("Total combinations without parse = " + list.size());

        return parseDuplicates(list);
    }

    private void getCombinations(char[] set, String prefix, int n, int boxes, ArrayList<int[]> list){

        if(boxes == 0){

            int[] code = new int[nbBoxes];

            for(int j = 0; j < nbBoxes; j++)
                code[j] = Integer.parseInt(String.valueOf(prefix.charAt(j)));

            list.add(code);
            return;
        }

        for(int i = 0; i < n; i++){
            String newPrefix = prefix + set[i];
            getCombinations(set, newPrefix, n, boxes - 1, list);
        }
    }

    private ArrayList<int[]> parseDuplicates(ArrayList<int[]> list){

        ArrayList<int[]> duplicateList = new ArrayList<>();

        for(int[] value : list){

            boolean duplicate = false;

            for(int i = 0; i < value.length; i++){

                for(int j = i + 1; j < value.length; j++){

                    if(i != value.length - 1) {
                        if (value[i] == value[j]) duplicate = true;
                    }
                    else{
                        if(value[i] == value[0]) duplicate = true;
                    }
                }
            }

            if(duplicate) duplicateList.add(value);
        }

        for(int[] value : duplicateList) list.remove(value);

        return list;
    }

    private void diplayCombinations(){

        for(int[] value : combinationList)
            System.out.println(Arrays.toString(value));
    }
}
