package main.java.view;

public class Display {

    public static void info(String info){
        System.out.println(info);
    }

    public static void error(String error){
        System.err.println(error);
    }

    public static void loading(String loaded){
        System.out.print("\r");
        System.out.print(loaded);
    }
}
