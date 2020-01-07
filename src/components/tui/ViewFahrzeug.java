package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class ViewFahrzeug extends AbstractMenu {

    ViewFahrzeug(IFachkonzept fachkonzept){
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
        System.out.println("Fahrzeuge anzeigen");
    }

    @Override
    public void showReachableMenus() {
//        OptionalInt longestNameLength = Arrays.stream(fahrzeuge).mapToInt(currentFahrzeug -> currentFahrzeug.getBezeichnung().length()).max();
//        int longestNameLengthInt = longestNameLength.orElse(0);
//        int longestIndex = String.valueOf(fahrzeuge.length).length();
//        int lengthOfSeparatorLine = longestNameLengthInt + 5 + longestIndex;
//        if (lengthOfSeparatorLine < 21) {
//            lengthOfSeparatorLine = 21;
//        }
//        String separatorLine = "-".repeat(lengthOfSeparatorLine);
//
//        System.out.println(separatorLine);
//
//        for (int i = 0; i < fahrzeuge.length; i++){
//            String name = fahrzeuge[i].getBezeichnung();
//            int nameWhitespaces = lengthOfSeparatorLine - name.length() - longestIndex - 2;
//            int indexWhitespaces = longestIndex - String.valueOf(i + 1).length();
//            System.out.println(
//                    name + nameWhitespaces +
//                    "(" + indexWhitespaces + (i + 1) + ")"
//            );
//        }
//
//        System.out.println(separatorLine);

        String separatorLine = "-".repeat(18);
        System.out.println(separatorLine);
        this.fachkonzept.getAllFahrzeuge().forEach(currFahrzeug -> {
            System.out.println(currFahrzeug.getFahrzeugId() + ":  " + currFahrzeug.getBezeichnung());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.print("Mehr Informationen zu\nFahrzeug (0 = Zurück)\n> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            new ExtViewFahrzeug(fachkonzept, choice);
            return true;
        } else return choice != 0;


    }
}
