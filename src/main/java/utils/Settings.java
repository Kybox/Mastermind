package main.java.utils;

import main.java.Mastermind;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static Properties SETTINGS;
    private static final String FILE = "main/resources/config.properties";
    private static final Logger LOG = LogManager.getLogger(Settings.class);

    public static Properties loadProperties(){

        LOG.info("Loading configuration file...");
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = Mastermind.class.getClassLoader().getResourceAsStream(FILE);

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

    public static int getBoxes(){

        Integer boxes = null;

        try{ boxes = Integer.parseInt(SETTINGS.getProperty("nbBoxes")); }
        catch (NumberFormatException | NullPointerException e) {
            LOG.error("Error in the configuration file for boxes");
        }
        return boxes;
    }

    public static int getMaxNumbers(){

        Integer nbNumbers = null;

        try { nbNumbers = Integer.parseInt(SETTINGS.getProperty("nbNumbers")); }
        catch (NumberFormatException e) { LOG.error("Error in the configutation file total numbers"); }
        catch (NullPointerException e) { LOG.error("Error in the configuration file for boxes"); }
        return nbNumbers;
    }
}
