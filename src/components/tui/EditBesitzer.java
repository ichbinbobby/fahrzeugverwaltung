package components.tui;

import concepts.IFachkonzept;
import models.Besitzer;
import utils.Console;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditBesitzer extends MainMenu {
    private List<Integer> ids = new ArrayList<Integer>();

    EditBesitzer(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzer bearbeiten");
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
            Besitzer besitzer = this.fachkonzept.getBesitzerDetails(this.ids.get(choice - 1));
            System.out.println("Neuer Name des Besitzers "+ besitzer.getName() +":\n(Leer/0 = Abbrechen)");
            String name = Console.inputString();

            if (name.isBlank() || name.equals("0")) {
                return true;
            } else {
                besitzer.setName(name);
                if (this.fachkonzept.saveBesitzer(besitzer) > 0){
                    System.out.println("Besitzer wurde unter dem Namen '" + name + "' gespeichert.");
                    Console.pressEnterToContinue();
                    return false;
                } else {
                    System.out.println("Es ist ein Fehler aufgetreten.");
                    Console.pressEnterToContinue();
                    return false;
                }
            }
        } else return choice != 0;
    }
}
