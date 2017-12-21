package main.java.game.player.ai;

import main.java.game.player.ai.strategy.Strategy;
import main.java.game.Game;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class AI {

    private int trials;
    private final int nbBoxes;
    private final int maxNumber;
    private int[] code;
    private String[] searchClues;
    private Strategy strategy;

    private static final Logger LOG = LogManager.getLogger(AI.class);

    public AI(){

        nbBoxes = Settings.getKeys();
        maxNumber = Settings.getMaxNumber();

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

    /**
     * Generate a new combination based on the selected game
     * @return  The new combination
     */
    public String generate(){

        StringBuilder strCode = new StringBuilder();

        // Mastermind
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
                    strCode.append(code[i]);
                }
            }
            else{
                code = strategy.getNewCode();
                for (int key : code) {
                    strCode.append(key);
                }
            }
        }

        // Search with +/-
        else if(Game.GAME_TYPE == 2) {
            for (int i = 0; i < nbBoxes; i++) {
                if (searchClues[i] != null) {
                    if (searchClues[i].equals("-")) code[i] = code[i] - 1;
                    else if (searchClues[i].equals("+")) code[i] = code[i] + 1;
                } else {
                    Random random = new Random();
                    code[i] = random.nextInt(maxNumber);
                }
                strCode.append(code[i]);
            }
        }

        trials++;
        return strCode.toString();
    }

    /**
     * Set clues to the AI Strategy or keep them in memory
     * @param   clues   The game indices
     */
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

    /**
     * Sets the indices according to the selected game
     * @param   secretCode  The secret combination to find
     * @param   userCode    The combination proposed by the user
     * @return              The clues
     */
    public String getClues(int[] secretCode, String userCode){

        StringBuilder clues = new StringBuilder();
        int[] code = SecretCode.convertToDigit(userCode);

        switch (Game.GAME_TYPE){

            // Mastermind
            case 1:
                int existing = 0;
                int wellPut = 0;

                for(int i = 0; i < secretCode.length; i++){
                    if(code[i] == secretCode[i]) wellPut++;
                    else for (int key : secretCode) if (code[i] == key) existing++;
                }

                String strExisting;
                if(existing > 1) strExisting = existing + " présents, ";
                else strExisting = existing + " présent, ";

                String strWellPut;
                if(wellPut > 1) strWellPut = wellPut + " bien placés.";
                else strWellPut = wellPut + " bien placé.";

                clues = new StringBuilder(strExisting + strWellPut);
                break;

            // Search game +/-
            case 2:
                for(int i = 0; i < secretCode.length; i++){
                    if(secretCode[i] < code[i]) clues.append("-");
                    else if(secretCode[i] == code[i]) clues.append("=");
                    else if(secretCode[i] > code[i]) clues.append("+");
                }
                break;

            default:
                LOG.fatal("Incorrect game type");
        }

        return clues.toString();
    }
}
