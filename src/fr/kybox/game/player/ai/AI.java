package fr.kybox.game.player.ai;

import fr.kybox.game.Game;
import fr.kybox.game.player.ai.strategy.Strategy;

import java.util.*;

public class AI {

    private int trials;
    private int nbBoxes;
    private int maxNumber;
    private int[] code;
    private String[] searchClues;
    private Strategy strategy;

    public AI(int nbBoxes, int maxNumber){

        this.nbBoxes = nbBoxes;
        this.maxNumber = maxNumber;

        // Set all digits to 0
        code = new int[nbBoxes];

        switch (Game.GAME_TYPE){

            case 1:
                strategy = new Strategy();
                strategy.initCombinations(nbBoxes, maxNumber);
                break;

            case 2:
                searchClues = new String[nbBoxes];
                break;
        }
    }

    public String generate(){

        String strCode = "";

        if(Game.GAME_TYPE == 1){

            if(trials == 0){
                for(int i = 0; i < nbBoxes; i++){
                    code[i] = new Random().nextInt(maxNumber);
                    while(true){
                        boolean exist = false;
                        for (int j = 0; j < i; j++){
                            if(code[j] == code[i]) {
                                code[i] = new Random().nextInt(maxNumber);
                                exist = true;
                                break;
                            }
                        }
                        if(!exist) break;
                    }
                    strCode = strCode + code[i];
                }
            }
            else{
                code = strategy.getNewCode();
                for(int i = 0; i < code.length; i++){
                    strCode = strCode + code[i];
                }
            }

            trials++;
            return strCode;
        }
        else if(Game.GAME_TYPE == 2) {
            for (int i = 0; i < nbBoxes; i++) {
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

        trials++;
        return strCode;
    }

    public void setClues(String clues){

        switch (Game.GAME_TYPE){
            case 1:
                strategy.setClues(clues, code);
                break;

            case 2:
                for(int i = 0; i < searchClues.length; i++)
                    searchClues[i] = String.valueOf(clues.charAt(i));
                break;
        }
    }

    public int getTrials(){

        return trials;
    }
}
