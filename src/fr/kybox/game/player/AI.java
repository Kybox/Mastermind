package fr.kybox.game.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AI {

    private int nbBoxes;
    private int maxNumber;
    private int[] code;
    private int game;
    private String[] searchClues;
    private ArrayList<int[]> combinationList;

    public AI(int nbBoxes, int maxNumber, int game){

        this.game = game;
        this.nbBoxes = nbBoxes;
        this.maxNumber = maxNumber;

        // Set all digits to 0
        code = new int[nbBoxes];

        if(game == 1){

            setCombinations();
        }
        else if(game == 2) searchClues = new String[nbBoxes];
    }

    public String generate(){

        String strCode = "";

        if(game == 1){


        }
        else if(game == 2) {
            for (int i = 0; i < nbBoxes; i++) {
                if (game == 2) {
                    if (searchClues[i] != null) {
                        if (searchClues[i].equals("-")) code[i] = code[i] - 1;
                        else if (searchClues[i].equals("+")) code[i] = code[i] + 1;
                    } else {
                        Random random = new Random();
                        code[i] = random.nextInt(maxNumber);
                    }
                    strCode = strCode + code[i];
                }
            }
        }
        return strCode;
    }

    public void setClues(String clues){

        if(game == 1){
            int existing = Integer.parseInt(String.valueOf(clues.charAt(0)));
            int wellPut = Integer.parseInt(String.valueOf(clues.charAt(2)));


        }
        else if(game == 2){
            for(int i = 0; i < searchClues.length; i++){
                searchClues[i] = String.valueOf(clues.charAt(i));
            }
        }
    }

    private void setCombinations(){

        char[] nbList = new char[maxNumber];

        for(int i = 0; i < maxNumber; i++)
            nbList[i] = Integer.toString(i).charAt(0);

        combinationList = new ArrayList<>();

        getCombinations(nbList, "", nbList.length, nbBoxes);

        combinationList = parseDuplicates(combinationList);

        for(int[] value : combinationList){
            System.out.println(Arrays.toString(value));
        }
    }

    private void getCombinations(char[] set, String prefix, int n, int boxes){

        if(boxes == 0){

            int[] code = new int[nbBoxes];

            for(int j = 0; j < nbBoxes; j++)
                code[j] = Integer.parseInt(String.valueOf(prefix.charAt(j)));

            combinationList.add(code);
            return;
        }

        for(int i = 0; i < n; i++){

            String newPrefix = prefix + set[i];

            getCombinations(set, newPrefix, n, boxes-1);
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

        for(int[] value : duplicateList){

            list.remove(value);
        }

        return list;
    }
}
