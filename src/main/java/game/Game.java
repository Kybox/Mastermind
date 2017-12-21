package main.java.game;

import main.java.game.player.human.Human;
import main.java.game.mode.Challenger;
import main.java.game.mode.Defender;
import main.java.game.mode.Duel;
import main.java.view.Display;

public class Game {

    public static int GAME_TYPE;
    public static int GAME_MODE;
    private final Human human;

    /**
     * Game class constructor
     */
    public Game(){

        human = new Human();

        GAME_TYPE = human.getGame();

        switch (GAME_TYPE){
            case 1:
                Display.info("Jeu sélectionné -> Mastermind");
                break;
            case 2:
                Display.info("Jeu sélectionné -> Recherche +/-");
                break;
        }

        GAME_MODE = human.getMode();

        startGameMode();
    }

    /**
     * Initialize the selected game mode
     */
    private void startGameMode(){

        switch (GAME_MODE){
            case 1:
                Display.info("Mode sélectionné -> Challenger");
                new Challenger(true);
                break;
            case 2:
                Display.info("Mode sélectionné -> Duel");
                new Duel();
                break;
            case 3:
                Display.info("Mode sélectionné -> Défenseur");
                new Defender(true);
                break;
        }

        endgameMenu();
    }

    /**
     * Display the end of game menu
     */
    private void endgameMenu() {

        Display.endgameMenu();

        switch (human.getEndgameSelection()){

            case 1: startGameMode();
                break;

            case 2: new Game();
                break;

            case 3: System.exit(0);
                break;
        }
    }
}