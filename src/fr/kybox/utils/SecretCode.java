package fr.kybox.utils;

import java.util.Random;

public class SecretCode {

    public static int[] generate(int boxes, int nbNumbers){

        int index = 0;
        Random random = new Random();
        int[] keys = new int[boxes];

        while (index < boxes){

            int key = random.nextInt(nbNumbers);

            keys[index] = key;
            index++;
        }

        return keys;
    }
}
