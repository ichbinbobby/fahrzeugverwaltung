package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteBesitzer extends MainMenu {
    private List<Integer> ids = new ArrayList<Integer>();

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
        var ref = new Object() {
            int counter = 1;
        };
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(ref.counter + ":  " + currBesitzer.getName());
            this.ids.add(currBesitzer.getBesitzerId());
            ref.counter = ref.counter + 1;
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        System.out.println("Besitzer auswählen\n(0 = Zurück)");
        int choice = Console.inputInt();

        if (choice != 0){
            if (this.fachkonzept.deleteBesitzer(this.ids.get(choice - 1))) {
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
