package fr.kybox.game;

import fr.kybox.controller.Controller;

public class Game {

    private Controller controller;

    public Game(){

        // Instance du controlleur
        controller = new Controller();

        int mode = controller.getMode();

        switch (mode){
            case 1:
                System.out.println("Mode sélectionné -> Challenger");
                break;
            case 2:
                System.out.println("Mode sélectionné -> Duel");
                break;
            case 3:
                System.out.println("Mode sélectionné -> Défenseur");
                break;
        }
    }
}
