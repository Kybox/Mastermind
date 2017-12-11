package fr.kybox.game.mode;

import fr.kybox.utils.SecretCode;
import fr.kybox.utils.Settings;

import java.util.Arrays;

public class Challenger {

    public Challenger(){

        int nbBoxes = Settings.getBoxes();
        int nbNumbers = Settings.getMaxNumbers();
        System.out.println("Start " + nbBoxes + " / " + nbNumbers);
        for(int i = 0; i < 50; i++) {
            int[] secretCode = SecretCode.generate(nbBoxes, nbNumbers);
            System.out.println(Arrays.toString(secretCode));
        }
    }

}
