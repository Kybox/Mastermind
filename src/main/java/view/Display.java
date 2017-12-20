package main.java.view;

import main.java.game.Game;
import main.java.utils.Settings;

public class Display {

    public static void info(String info){
        System.out.println(info);
    }

    public static void error(String error){
        System.err.println(error);
    }

    public static void mainMenuSelections(){
        Display.info("Sélectionner un jeu :");
        Display.info("---------------------");
        Display.info("[1] - Mastermind");
        Display.info("[2] - Recherche +/-");
    }

    public static void gameModeSelections(){
        Display.info("");
        Display.info("Sélectionner un mode de jeu :");
        Display.info("-----------------------------");
        Display.info("[1] - Challenger -> vous devez trouver la combinaison secrète de l'ordinateur");
        Display.info("[2] - Duel -> l'ordinateur et vous jouez tour à tour pour trouver la combinaison secrète");
        Display.info("[3] - Défenseur -> c'est à l'ordinateur de trouver votre combinaison secrète");
    }

    public static void secretCombinationSelection(){
        Display.info("-----------------------------");
        Display.info("Sélectionner votre combinaison secrête :");
        Display.info("(" + Settings.getKeys() + " chiffres compris entre 0 et "
                        + (Settings.getMaxNumbers() - 1) + ")");
    }

    public static void invalidCombination(){
        int nbKeys = Settings.getKeys();
        int maxNumbers = Settings.getMaxNumbers();
        Display.info("Vous devez saisir une combinaison de " + nbKeys
                + " chiffres inférieurs ou égals à " + (maxNumbers - 1));
    }

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

    public static void invalidClues(){
        Display.error("\tVotre indice n'est pas correct !");
        Display.error("\tMerci de vérifier votre réponse.");
    }

    public static void invalidMenuSelection(){
        Display.error("\tVeuillez saisir 1, 2 ou 3 suivant votre choix de réponse");
    }
}
