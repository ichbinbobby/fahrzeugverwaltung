package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewBesitzer extends MainMenu {
    private List<Integer> ids = new ArrayList<Integer>();

    ViewBesitzer(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzter anzeigen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(21);
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
        System.out.println("Mehr Informationen zu\nBesitzer (0 = Zurück)");
        int choice = Console.inputInt();
        if (choice != 0){
            new ExtViewBesitzer(fachkonzept, this.ids.get(choice - 1)).showMenu();
            return true;
        } else return choice != 0;
    }
}
