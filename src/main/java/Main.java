package main.java;

import main.java.game.Game;
import main.java.utils.Settings;
import main.java.view.Display;

public class Main {

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
