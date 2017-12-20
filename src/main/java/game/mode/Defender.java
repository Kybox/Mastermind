package main.java.game.mode;

import main.java.game.player.human.Human;
import main.java.game.player.ai.AI;
import main.java.utils.SecretCode;
import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Arrays;

public class Defender implements IGameMode {

    private int trials;
    private boolean win;
    private boolean finished;

    private int[] secretCode;
    private final AI computer;
    private final int maxTrials;
    private final Human human;
    private final boolean autoManagement;

    /**
     * The Defender constructor
     * @param   autoManagement  Indicates if the class manages itself the game rounds
     */
    public Defender(boolean autoManagement){

        this.autoManagement = autoManagement;

        human = new Human();
        maxTrials = Settings.getTrials();
        computer = new AI();

        setSecretCode();
        startGame();
    }

    @Override
    public void startGame(){

        if(autoManagement) {

            do { gameTour(); }
            while (!finished);

            Display.gameOver(win, Arrays.toString(secretCode), trials - 1);
        }
    }

    @Override
    public boolean gameTour(){

        trials++;

        if(trials > maxTrials) {
            win = false;
            finished = true;
        }
        else {
            Display.info("-----------------------------");
            Display.info("Proposition de l'ordinateur : ");

            String newCode = computer.generate();
            System.out.println(newCode);

            if (!SecretCode.isEqual(secretCode, newCode)) {

                Display.info("Votre réponse :");
                String clues = human.getClues(newCode, secretCode);
                computer.setClues(clues);
            }
            else {
                win = true;
                finished = true;
            }
        }

        return finished;
    }

    /**
     * Generate a secret combinaition
     */
    private void setSecretCode(){
        String code = human.getSecretCode();
        secretCode = SecretCode.convertToDigit(code);
    }

    /**
     * Retrieves the generated secret combination
     * @return  The secret combination
     */
    public int[] getSecretCode(){
        return secretCode;
    }

    /**
     * Retrieves the number of trials
     * @return  Trials
     */
    public int getTrials(){
        return trials;
    }
}
