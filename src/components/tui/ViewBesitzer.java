package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class ViewBesitzer extends AbstractMenu {

    ViewBesitzer(IFachkonzept fachkonzept){
        this.fachkonzept = fachkonzept;
        boolean run = true;
        while (run){
            showMenuInfo();
            showReachableMenus();
            run = getUserChoice();
        }
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzter anzeigen");
    }

//    @Override
//    public void showReachableMenus() {
//        OptionalInt longestNameLength = besitzer.mapToInt(currentBesitzer -> currentBesitzer.getName().length()).max();
//        int longestNameLengthInt = longestNameLength.orElse(0);
//        int longestIndex = String.valueOf(besitzer.count()).length();
//        int lengthOfSeparatorLine = longestNameLengthInt + 5 + longestIndex;
//        if (lengthOfSeparatorLine < 21) {
//            lengthOfSeparatorLine = 21;
//        }
//        String separatorLine = "-".repeat(lengthOfSeparatorLine);
//
//        System.out.println(separatorLine);
//
//        for (int i = 0; i < besitzer.count(); i++){
//
//            String name = besitzer[i].getName();
//            int nameWhitespaces = lengthOfSeparatorLine - name.length() - longestIndex - 2;
//            int indexWhitespaces = longestIndex - String.valueOf(i + 1).length();
//            System.out.println(
//                    name + nameWhitespaces +
//                    "(" + indexWhitespaces + (i + 1) + ")"
//            );
//        }
//
//        besitzer.forEach(currBesizter -> {
//            String name = currBesizter.getName();
//            int nameWhitespaces = lengthOfSeparatorLine - name.length() - longestIndex - 2;
//            int indexWhitespaces = longestIndex - String.valueOf(i + 1).length();
//            System.out.println(
//                    name + nameWhitespaces +
//                            "(" + indexWhitespaces + (i + 1) + ")"
//            );
//        });
//
//        System.out.println(separatorLine);
//    }


    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(21);
        System.out.println(separatorLine);
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(currBesitzer.getBesitzerId() + ":  " + currBesitzer.getName());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.println("Mehr Informationen zu\nBesitzer (0 = Zurück)");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        System.out.print("> ");
        choice = input.nextInt();

        if (choice != 0){
            new ExtViewBesitzer(fachkonzept, choice);
            return true;
        } else return choice != 0;
    }
}
