package main.java.utils;

import main.java.Main;
import main.java.view.Display;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Kybox
 * @version 1.0
 */

public class Settings {

    private static Properties SETTINGS;
    private static boolean devMode = false;
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
    public static int getKeys(){

        int nbKeys = 0;

        try{ nbKeys = Integer.parseInt(SETTINGS.getProperty("nbKeys")); }
        catch (NumberFormatException | NullPointerException e) {
            LOG.error("Error in the configuration file for nbKeys");
        }
        return nbKeys;
    }

    /**
     * Get the maximum value of the digits
     * @return  The maximum value
     */
    public static int getMaxNumber(){

        int maxNumber = 0;

        try { maxNumber = Integer.parseInt(SETTINGS.getProperty("maxNumber")); }
        catch (NumberFormatException e) { LOG.error("Error in the configutation file, wrong number format"); }
        catch (NullPointerException e) { LOG.error("Error in the configuration file range of numbers"); }
        if(maxNumber < 4) maxNumber = 4;
        else if(maxNumber > 10) maxNumber = 10;
        return maxNumber;
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

    /**
     * Check if developer mode is enabled
     * @return  True if it's activated otherwhise false
     */
    public static boolean isDevMode(){
        if(!devMode) return Boolean.parseBoolean(SETTINGS.getProperty("devMode"));
        else return true;
    }

    /**
     * Sets the developer mode (from the Main class)
     */
    public static void setDevMode(){
        Display.info("Mode développeur");
        devMode = true;
    }
}
