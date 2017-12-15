package main.java;

import main.java.game.Game;
import main.java.utils.Settings;
import main.java.view.Display;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        System.setProperty("log4j2.configurationFile", "main/resources/log4j2.xml");
        Display.info("Main + Jeu de réflexion v1");

        if(Settings.loadProperties() != null) { new Game(); }
        else LOG.info("Not properties loaded, exit the application");
    }
}
