package main.java.view;

import main.java.game.Game;
import main.java.utils.Settings;

public class Display {

    /**
     * Displays an informational message
     * @param   info    The message to display
     */
    public static void info(String info){

        System.out.println(info);
    }

    /**
     * Displays an error message
     * @param   error   The error to display
     */
    public static void error(String error){

        System.err.println(error);
    }

    /**
     * Displays the main menu for game selection
     */
    public static void mainMenuSelections(){
        Display.info("");
        Display.info("+--------------------+");
        Display.info("|   MENU PRINCIPAL   |");
        Display.info("+--------------------+");
        Display.info("");
        Display.info("Sélectionner un jeu :");
        Display.info("---------------------");
        Display.info("[1] - Mastermind");
        Display.info("[2] - Recherche +/-");
    }

    /**
     * Displays the game mode selection menu
     */
    public static void gameModeSelections(){
        Display.info("");
        Display.info("Sélectionner un mode de jeu :");
        Display.info("-----------------------------");
        Display.info("[1] - Challenger -> vous devez trouver la combinaison secrète de l'ordinateur");
        Display.info("[2] - Duel -> l'ordinateur et vous jouez tour à tour pour trouver la combinaison secrète");
        Display.info("[3] - Défenseur -> c'est à l'ordinateur de trouver votre combinaison secrète");
    }

    /**
     * Displays the indications for entering a secret combination
     */
    public static void secretCombinationSelection(){
        Display.info("-----------------------------");
        Display.info("Sélectionner votre combinaison secrête :");
        Display.info("(" + Settings.getKeys() + " chiffres compris entre 0 et "
                        + (Settings.getMaxNumber() - 1) + ")");
    }

    public static void computerCombination(String combination){
        String line = "";
        for(int i=0; i < combination.length(); i++)
            line = line + "-";

        Display.info("");
        Display.info("+-----------------------------+");
        Display.info("| Proposition de l'ordinateur |");
        Display.info("+-----------------------------+");
        Display.info("| -> " + combination + " <- |");
        Display.info("+----" + line + "----+");
    }

    public static void clues(String clues){
        Display.info("");
        Display.info("+-------------------------+");
        Display.info("| Réponse de l'ordinateur |");
        Display.info("+-------------------------+");
        Display.info(clues);
        Display.info("");
    }

    public static void typeCombination(){
        Display.info("");
        Display.info("+------------------------+");
        Display.info("| Saisir une combinaison |");
        Display.info("+------------------------+");
    }

    /**
     * Displays an error message about the combination
     */
    public static void invalidCombination(){
        int nbKeys = Settings.getKeys();
        int maxNumbers = Settings.getMaxNumber();
        Display.error("Vous devez saisir une combinaison de " + nbKeys
                + " chiffres inférieurs ou égals à " + (maxNumbers - 1));
    }

    /**
     * Displays an error message regarding clues
     */
    public static void invalidFormat(){
        switch (Game.GAME_TYPE){
            case 1:
                Display.error("Vous devez saisir 2 indices séparés par une virgule :");
                Display.error("\tle premier pour le nombre de chiffre bien placés,");
                Display.error("\tet le second pour le nombre de chiffres présents mal placés");
                Display.error("\tExemple : 1 bien placé & 2 présents donne -> 1,2");
                break;
            case 2:
                Display.error("Vous devez saisir un indice (- ou = ou +) pour chaque chiffre");
                Display.error("\tExemple : +--=");
        }
    }

    /**
     * Displays an error message indicating that the clues are incorrect
     */
    public static void invalidClues(){
        Display.error("\tVotre indice n'est pas correct !");
        Display.error("\tMerci de vérifier votre réponse.");
    }

    /**
     * Displays an error message about the menu after the end of a game
     */
    public static void invalidMenuSelection(){
        Display.error("\tVeuillez saisir 1, 2 ou 3 suivant votre choix de réponse");
    }

    /**
     * Displays the result of the game
     * @param   win       If the game has been won
     * @param   code      The winning combination
     * @param   trials    The number of rounds played
     */
    public static void gameOver(boolean win, String code, int trials){

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

    }

    /**
     * Displays the endgame menu
     */
    public static void endgameMenu(){

        Display.info("");
        Display.info("+------------------+");
        Display.info("| Fin de la partie |");
        Display.info("+------------------+");
        Display.info("");
        Display.info("Faites votre choix parmis les propositions suivantes :");
        Display.info("\t-> [1] - Recommencer une nouvelle partie");
        Display.info("\t-> [2] - Retour au menu principal");
        Display.info("\t-> [3] - Quitter l'application");
    }
}
