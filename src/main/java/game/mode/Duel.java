package main.java.game.mode;

import main.java.view.Display;

import java.util.Arrays;

public class Duel implements IGameMode {

    private Defender defender;
    private Challenger challenger;
    private boolean blnFinished;
    private boolean gameWon;
    private int[] secretCode;
    private int trials;

    public Duel(){

        defender = new Defender();
        challenger = new Challenger();
    }

    @Override
    public void startGame(){

        do{ gameTour(); }
        while (!blnFinished);

        Display.gameOver(gameWon, Arrays.toString(secretCode), trials);
    }

    @Override
    public boolean gameTour() {

        gameWon = false;

        if(challenger.gameTour()){
            secretCode = challenger.getSecretCode();
            trials = challenger.getTrials();
            gameWon = true;
            blnFinished = true;
        }
        else if(defender.gameTour()){
            secretCode = defender.getSecretCode();
            trials = defender.getTrials();
            gameWon = false;
            blnFinished = true;
        }

        return blnFinished;
    }
}
