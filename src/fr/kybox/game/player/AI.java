package fr.kybox.game.player;

import java.util.Random;

public class AI {

    private int nbBoxes;
    private int maxNumber;
    private int[] code;
    private int game;
    private String[] searchClues;

    public AI(int nbBoxes, int maxNumber, int game){

        this.game = game;
        this.nbBoxes = nbBoxes;
        this.maxNumber = maxNumber;

        // Set all digits to 0
        code = new int[nbBoxes];

        if(game == 2) searchClues = new String[nbBoxes];
    }

    public String generate(){

        String strCode = "";
        for(int i = 0; i < nbBoxes; i++){
            if(game == 2){
                if(searchClues[i] != null){
                    if(searchClues[i].equals("-")) code[i] = code[i] - 1;
                    else if(searchClues[i].equals("+")) code[i] = code[i] + 1;
                }
                else {
                    Random random = new Random();
                    code[i] = random.nextInt(maxNumber);
                }
                strCode = strCode + code[i];
            }
        }

        return strCode;
    }

    public void setClues(String clues){
        if(game == 2){
            for(int i = 0; i < searchClues.length; i++){
                searchClues[i] = String.valueOf(clues.charAt(i));
            }
        }
    }
}
