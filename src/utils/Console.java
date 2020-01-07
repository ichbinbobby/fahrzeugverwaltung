package utils;

import java.util.Scanner;

public final class Console {
    private static final String newlines = System.lineSeparator().repeat(50);
    public static void clear(){
        System.out.println(newlines);
    }

    public static void pressEnterToContinue(){
        Scanner input = new Scanner(System.in);
        System.out.println("Drücke ENTER um fortzufahren.");
        input.nextLine();
    }
}
