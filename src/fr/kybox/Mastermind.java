package fr.kybox;

import fr.kybox.game.Game;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Mastermind {

    private static Logger logger = LogManager.getLogger(Mastermind.class);

    public static void main(String[] args) {

        logger.debug("msg de debug");
        logger.info("msg d'info");
        logger.warn("msg warning");
        logger.error("msg error");
        logger.fatal("msg fatal");

        System.out.println("Mastermind v1");

        Game game = new Game();
    }
}
