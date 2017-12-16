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
                case 1:
                    Display.info("+----------------------------------------------------+");
                    Display.info("| Gagné ! Vous avez découvert la combinaison secrète |");
                    Display.info("+----------------------------------------------------+");
                    break;

                case 2:
                    Display.info("+----------------------------------------------------+");
                    Display.info("| Gagné ! Vous avez découvert la combinaison secrète |");
                    Display.info("+----------------------------------------------------+");
                    break;

                case 3:
                    Display.info("+------------------------------------------------------------+");
                    Display.info("| Perdu ! L'ordinateur a découvert votre combinaison secrète |");
                    Display.info("+------------------------------------------------------------+");
                    break;
            }

            Display.info("  Combinaison secrète : " + code);
            Display.info("  Nombre d'essais : " + trials + " / " + Settings.getTrials());
            Display.info("");
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
                    Display.info("| Gagné ! L'ordinateur a découvert votre combinaison secrète |");
                    Display.info("+------------------------------------------------------------+");
                    break;

                case 3:
                    Display.info("+------------------------------------------------------------------+");
                    Display.info("| Gagné ! L'ordinateur n'a pas découvert votre combinaison secrète |");
                    Display.info("+------------------------------------------------------------------+");
                    break;
            }

            Display.info("  Combinaison secrète : " + code);
            Display.info("  Nombre d'essais : " + trials + " / " + Settings.getTrials());
            Display.info("");
        }

        setChanged();
        notifyObservers();
    }
}
