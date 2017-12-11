package fr.kybox.game;

import fr.kybox.controller.Controller;
import fr.kybox.game.mode.Challenger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Game {

    private static final Logger LOG = LogManager.getLogger(Game.class);

    public Game(){

        Controller controller = new Controller();

        int game = controller.getGame();

        switch (game){
            case 1:
                System.out.println("Jeu sélectionné -> Mastermind");
                break;
            case 2:
                System.out.println("Jeu sélectionné -> Recherche +/-");
                break;
        }

        int mode = controller.getMode();

        switch (mode){
            case 1:
                System.out.println("Mode sélectionné -> Challenger");
                new Challenger(game);
                break;
            case 2:
                System.out.println("Mode sélectionné -> Duel");
                break;
            case 3:
                System.out.println("Mode sélectionné -> Défenseur");
                break;
        }
    }
}
