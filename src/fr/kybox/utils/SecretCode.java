package fr.kybox.utils;

public class SecretCode {

    public static int[] generate(){

        int index = 0;
        int[] keys = new int[4];

        while (index < 4){

            boolean insert = true;
            int key = (int) (10 * Math.random());

            for(int value : keys){
                if(value == key) {
                    insert = false;
                    break;
                }
            }

            if(insert) {
                keys[index] = key;
                index++;
            }
        }

        return keys;
    }
}
