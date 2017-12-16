package main.java.game.mode;

import main.java.game.GameOver;
import main.java.utils.Settings;

import java.util.Arrays;

public class Duel {

    private GameOver gameOver;
    private Defender defender;
    private Challenger challenger;

    public Duel(GameOver gameOver){

        this.gameOver = gameOver;
        defender = new Defender(gameOver);
        defender.setAutoManagement(false);

        challenger = new Challenger(gameOver);
        challenger.setAutoManagement(false);
    }

    public void startGame(){

        int trials = 0;
        boolean gameWon = false;
        boolean finished = false;
        int[] secretCode = new int[Settings.getBoxes()];

        do{
            if(challenger.gameTour()){
                secretCode = challenger.getSecretCode();
                trials = challenger.getTrials();
                gameWon = true;
                finished = true;
            }
            else if(defender.gameTour()){
                secretCode = defender.getSecretCode();
                trials = defender.getTrials();
                gameWon = false;
                finished = true;
            }
        }
        while (!finished);

        gameOver.display(gameWon, Arrays.toString(secretCode), trials);
    }

    public void setSecretCode(){

        defender.setSecretCode();
        challenger.setSecretCode();
    }
}
