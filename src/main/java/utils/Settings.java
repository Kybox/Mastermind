package main.java.utils;

import main.java.Main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static Properties SETTINGS;
    private static final String FILE = "main/resources/config.properties";
    private static final Logger LOG = LogManager.getLogger(Settings.class);

    /**
     * Load the configuration file
     * @return  The game settings
     */
    public static Properties loadProperties(){

        LOG.info("Loading configuration file...");
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            // Check si input(file)
            inputStream = Main.class.getClassLoader().getResourceAsStream(FILE);

            if(inputStream != null) {
                properties.load(inputStream);
                SETTINGS = properties;
                LOG.info("Loading complete");
            }
            else throw new IOException();
        }
        catch (IOException e) { LOG.error("InputStream is NULL, can't reach the resource " + FILE); }
        finally {
            if(inputStream != null){
                try { inputStream.close(); }
                catch (IOException e) { LOG.error("General IO Exception", e); }

            }
        }

        return SETTINGS;
    }

    /**
     * Get the number of keys that make up the combination
     * @return  The number of keys
     */
    public static int getBoxes(){

        int nbKeys = 0;

        try{ nbKeys = Integer.parseInt(SETTINGS.getProperty("nbBoxes")); }
        catch (NumberFormatException | NullPointerException e) {
            LOG.error("Error in the configuration file for nbKeys");
        }
        return nbKeys;
    }

    /**
     * Get the maximum value of the digits
     * @return  The maximum value
     */
    public static int getMaxNumbers(){

        int nbNumbers = 0;

        try { nbNumbers = Integer.parseInt(SETTINGS.getProperty("nbNumbers")); }
        catch (NumberFormatException e) { LOG.error("Error in the configutation file, wrong number format"); }
        catch (NullPointerException e) { LOG.error("Error in the configuration file range of numbers"); }
        return nbNumbers;
    }

    /**
     * Get the maximum number of turns for each game played
     * @return  The maximum number of trials
     */
    public static int getTrials(){

        int nbTrials = 0;

        try { nbTrials = Integer.parseInt(SETTINGS.getProperty("nbTrials")); }
        catch (NumberFormatException e) { LOG.error("Error in the configutation file, wrong number format"); }
        catch (NullPointerException e) { LOG.error("Error in the configuration file for trials"); }
        return nbTrials;
    }
}
