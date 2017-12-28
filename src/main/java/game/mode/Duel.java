package main.java.game.mode;

import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

/**
 * @author Kybox
 * @version 1.0
 */

public class Duel implements GameMode {

    private final Defender defender;
    private final Challenger challenger;
    private boolean blnFinished;
    private boolean gameWon;
    private boolean isNull;
    private int[] secretCode;
    private int trials;

    public Duel(){

        defender = new Defender();
        challenger = new Challenger();
    }

    @Override
    public void startGame(){

        if(Settings.isDevMode())
            Display.secretCode(Arrays.toString(challenger.getSecretCode()));

        do{ gameTour(); }
        while (!blnFinished);

        if(!challenger.isGameWon() && !defender.isGameWon() && trials == Settings.getTrials()) isNull = true;
        Display.gameOver(gameWon, Arrays.toString(secretCode), trials, isNull);
    }

    @Override
    public boolean gameTour() {

        gameWon = false;

        if(challenger.gameTour()){
            secretCode = challenger.getSecretCode();
            trials = challenger.getTrials();
            gameWon = challenger.isGameWon();
            blnFinished = true;
        }
        else if(defender.gameTour()){
            secretCode = defender.getSecretCode();
            trials = defender.getTrials();
            gameWon = !defender.isGameWon();
            blnFinished = true;
        }

        return blnFinished;
    }

    @Override
    public boolean isGameWon(){

        if(challenger.isGameWon()) return true;
        else if(!defender.isGameWon()) return true;
        else return false;
    }
}
