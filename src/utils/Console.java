package utils;

import java.util.Scanner;

public final class Console {
    private static final String newlines = System.lineSeparator().repeat(50);
    public static void clear(){
        System.out.println(newlines);
    }

    public static void pressEnterToContinue(){
        Scanner input = new Scanner(System.in);
        System.out.print("Drücke ENTER um fortzufahren.");
        input.nextLine();
    }

    public static int inputInt(){
        Scanner input = new Scanner(System.in);
        System.out.print("> ");
        while (!input.hasNextInt()){
            System.out.println("Keine gültige Eingabe");
            input.next();
            System.out.print("> ");
        }
        return input.nextInt();
    }

    public static String inputString(){
        Scanner input = new Scanner(System.in);
        System.out.print("> ");
        while (!input.hasNextLine()){
            System.out.println("Keine gültige Eingabe");
            input.next();
            System.out.print("> ");
        }
        return input.nextLine().trim();
    }
}
