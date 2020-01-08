package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteFahrzeug extends MainMenu {
    private List<Integer> ids = new ArrayList<Integer>();

    DeleteFahrzeug(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Fahrzeug löschen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(16);
        System.out.println(separatorLine);
        var ref = new Object() {
            int counter = 1;
        };
        this.fachkonzept.getAllFahrzeuge().forEach(currFahrzeug -> {
            System.out.println(ref.counter + ":  " + currFahrzeug.getBezeichnung());
            this.ids.add(currFahrzeug.getFahrzeugId());
            ref.counter = ref.counter + 1;
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        System.out.println("Fahrzeug auswählen\n(0 = Zurück)");
        int choice = Console.inputInt();

        if (choice != 0){
            if (this.fachkonzept.deleteFahrzeug(this.ids.get(choice - 1))) {
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
