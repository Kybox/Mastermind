package main.java.game.mode;

import main.java.game.GameOver;
import main.java.utils.Settings;

import java.util.Arrays;

public class Duel implements IGameMode {

    private GameOver gameOver;
    private Defender defender;
    private Challenger challenger;
    private boolean blnFinished;
    private boolean gameWon;
    private int[] secretCode;
    private int trials;

    public Duel(GameOver gameOver){

        this.gameOver = gameOver;

        defender = new Defender(gameOver, false);
        challenger = new Challenger(gameOver, false);

        setSecretCode();
        startGame();
    }

    @Override
    public void startGame(){

        do{ gameTour(); }
        while (!blnFinished);

        gameOver.display(gameWon, Arrays.toString(secretCode), trials);
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

    @Override
    public void setSecretCode(){

        defender.setSecretCode();
        challenger.setSecretCode();
    }
}
