package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class DeleteBesitzer extends MainMenu {

    DeleteBesitzer(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzer löschen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(19);
        System.out.println(separatorLine);
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(currBesitzer.getBesitzerId() + ":  " + currBesitzer.getName());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        System.out.println("Besitzer auswählen\n(0 = Zurück)");
        int choice = Console.inputInt();

        if (choice != 0){
            if (this.fachkonzept.deleteBesitzer(choice)) {
                System.out.println("Löschen erfolgreich.");
                Console.pressEnterToContinue();
                return false;
            } else {
                System.out.println("Es gab einen Fehler.");
                Console.pressEnterToContinue();
                return true;
            }
        } else return choice != 0;
    }
}
