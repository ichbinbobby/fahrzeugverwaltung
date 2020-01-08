package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewFahrzeug extends MainMenu {
    private List<Integer> ids = new ArrayList<Integer>();

    ViewFahrzeug(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Fahrzeuge anzeigen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(18);
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
        System.out.println("Mehr Informationen zu\nFahrzeug (0 = Zurück)");
        int choice = Console.inputInt();

        if (choice != 0){
            new ExtViewFahrzeug(fachkonzept, this.ids.get(choice - 1)).showMenu();
            return true;
        } else return choice != 0;


    }
}
