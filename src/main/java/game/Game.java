package main.java.game;

import main.java.controller.Controller;
import main.java.game.mode.Challenger;
import main.java.game.mode.Defender;
import main.java.game.mode.Duel;
import main.java.view.Display;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Observable;
import java.util.Observer;

public class Game implements Observer{

    public static int GAME_TYPE;
    public static int GAME_MODE;
    private GameOver gameOver;
    private Controller controller;
    private static final Logger LOG = LogManager.getLogger(Game.class);

    public Game(){

        gameOver = new GameOver();
        gameOver.addObserver(this);

        Display.info("");
        Display.info("+--------------------+");
        Display.info("|   MENU PRINCIPAL   |");
        Display.info("+--------------------+");
        Display.info("");
        controller = new Controller();

        GAME_TYPE = controller.getGame();

        switch (GAME_TYPE){
            case 1:
                Display.info("Jeu sélectionné -> Mastermind");
                break;
            case 2:
                Display.info("Jeu sélectionné -> Recherche +/-");
                break;
        }

        GAME_MODE = controller.getMode();

        startGame();
    }

    private void startGame(){

        switch (GAME_MODE){
            case 1:
                Display.info("Mode sélectionné -> Challenger");
                new Challenger(gameOver, true);
                break;
            case 2:
                Display.info("Mode sélectionné -> Duel");
                new Duel(gameOver);
                break;
            case 3:
                Display.info("Mode sélectionné -> Défenseur");
                new Defender(gameOver, true);
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        switch (controller.getMenuSelection()){

            case 1: startGame();
                break;

            case 2: new Game();
                break;

            case 3: System.exit(0);
                break;
        }
    }
}
