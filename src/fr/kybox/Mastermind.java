package fr.kybox;

import fr.kybox.game.Game;
import fr.kybox.utils.Settings;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Mastermind {

    private static final Logger LOG = LogManager.getLogger(Mastermind.class);

    public static void main(String[] args) {

        LOG.info("Mastermind + Jeu de réflexion v1");

        if(Settings.loadProperties() != null) { Game game = new Game(); }
        else LOG.info("Not properties loaded, exit the application");
    }
}
