package main.java;

import main.java.game.Game;
import main.java.utils.Settings;
import main.java.view.Display;

/**
 * @author Kybox
 * @version 1.0
 */

public class Main {

    /**
     * Main method to start the application
     * @param args  You can add "devMode" to display the computers's combination
     */
    public static void main(String[] args) {

        System.setProperty("log4j2.configurationFile", "main/resources/log4j2.xml");
        Display.info("Mastermind + Jeu de réflexion v1");

        if(args.length != 0)
            if(String.valueOf(args[0]).toLowerCase().equals("devmode"))
                Settings.setDevMode();

        if(Settings.loadProperties() != null) new Game();
        else Display.error("Error loading configuration file");
    }
}
