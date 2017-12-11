package fr.kybox.controller;

import fr.kybox.utils.CheckInput;

import java.util.Scanner;

public class Controller {

    private Scanner scanner;

    public Controller(){

        scanner = new Scanner(System.in);
    }

    public String getInput(){
        return scanner.nextLine();
    }

    public int getMode(){

        System.out.println("Sélectionner un mode de jeu :");
        System.out.println("[1] - Challenger");
        System.out.println("[2] - Duel");
        System.out.println("[3] - Défenseur");

        int mode;

        while (true){
            String line = scanner.nextLine();
            if(!CheckInput.gameMode(line))
                System.out.println("Saissir le numéro correspondant au mode de jeu souhaité");
            else {
                mode = Integer.parseInt(line);
                break;
            }
        }
        return mode;
    }
}
