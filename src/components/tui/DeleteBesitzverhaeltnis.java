package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteBesitzverhaeltnis extends MainMenu {
    private int tmpBesitzerId;
    private boolean abbrechen = false;
    private List<Integer> besitzerIds = new ArrayList<Integer>();
    private List<Integer> fahrzeugIds = new ArrayList<Integer>();

    DeleteBesitzverhaeltnis(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzverhältnis löschen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(24);
        boolean run = true;
        System.out.println(separatorLine);
        while (run) {
            run = chooseBesitzer();
        }
        if (this.abbrechen){
            return;
        }
        showMenuInfo();
        System.out.println(separatorLine);
        System.out.println("-2/2 Fahrzeug auswählen");
        System.out.println(separatorLine);
        var ref = new Object() {
            int counter = 1;
        };
        this.fachkonzept.getFahrzeugeByBesitzer(this.tmpBesitzerId).forEach(currFahrzeug -> {
            System.out.println(ref.counter + ":  " + currFahrzeug.getBezeichnung());
            this.fahrzeugIds.add(currFahrzeug.getFahrzeugId());
            ref.counter = ref.counter + 1;
        });
        System.out.println(separatorLine);
    }

    private boolean chooseBesitzer(){
        String separatorLine = "-".repeat(24);
        System.out.println("-1/2 Besitzer auswählen");
        System.out.println(separatorLine);
        var ref = new Object() {
            int counter = 1;
        };
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(ref.counter + ":  " + currBesitzer.getName());
            this.besitzerIds.add(currBesitzer.getBesitzerId());
            ref.counter = ref.counter + 1;
        });
        System.out.println(separatorLine);
        System.out.println("Besitzer auswählen\n(0 = Abbrechen)");
        int choice = Console.inputInt();

        if (choice > 0){
            this.tmpBesitzerId = this.besitzerIds.get(choice-1);
            return false;
        } else if (choice == 0) {
            this.abbrechen = true;
            return false;
        } else {
                showMenuInfo();
                System.out.println("Ein Fehler ist aufgetreten.");
                Console.pressEnterToContinue();
                return true;
        }
    }

    @Override
    public boolean getUserChoice() {
        if (this.abbrechen) {
            return false;
        }
        System.out.println("Fahrzeug auswählen\n(0 = Zurück)");
        int choice = Console.inputInt();

        if (choice > 0){
            if (this.fachkonzept.setNewBesitzer(this.fahrzeugIds.get(choice - 1), -1)) {
                System.out.println("Löschen erfolgreich.");
                Console.pressEnterToContinue();
                return false;
            } else {
                System.out.println("Es gab einen Fehler.");
                Console.pressEnterToContinue();
                return true;
            }
        } else if(choice == 0){
            return true;
        } else return true;
    }
}

