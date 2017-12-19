package main.java.game;

import main.java.utils.Settings;
import main.java.view.Display;

import java.util.Observable;

public class GameOver extends Observable {

    public GameOver(){ }

    public void display(boolean win, String code, int trials){

        if (win) {
            Display.info("");

            switch (Game.GAME_MODE){

                case 3:
                    Display.info("+------------------------------------------------------------+");
                    Display.info("| Perdu ! L'ordinateur a découvert votre combinaison secrète |");
                    Display.info("+------------------------------------------------------------+");
                    break;

                default:
                    Display.info("+----------------------------------------------------+");
                    Display.info("| Gagné ! Vous avez découvert la combinaison secrète |");
                    Display.info("+----------------------------------------------------+");
                    break;
            }
        }
        else{

            switch (Game.GAME_MODE){
                case 1:
                    Display.info("+----------------------------------------------------------+");
                    Display.info("| Perdu ! Vous n'avez pas découvert la combinaison secrète |");
                    Display.info("+----------------------------------------------------------+");
                    break;

                case 2:
                    Display.info("+------------------------------------------------------------+");
                    Display.info("| Perdu ! L'ordinateur a découvert votre combinaison secrète |");
                    Display.info("+------------------------------------------------------------+");
                    break;

                case 3:
                    Display.info("+------------------------------------------------------------------+");
                    Display.info("| Gagné ! L'ordinateur n'a pas découvert votre combinaison secrète |");
                    Display.info("+------------------------------------------------------------------+");
                    break;
            }
        }

        Display.info("  Combinaison secrète : " + code);
        Display.info("  Nombre d'essais : " + trials + " / " + Settings.getTrials());
        Display.info("");
        Display.info("+------------------+");
        Display.info("| Fin de la partie |");
        Display.info("+------------------+");
        Display.info("");
        Display.info("Faites votre choix parmis les propositions suivantes :");
        Display.info("\t-> [1] - Recommencer une nouvelle partie");
        Display.info("\t-> [2] - Retour au menu principal");
        Display.info("\t-> [3] - Quitter l'application");

        setChanged();
        notifyObservers();
    }
}
