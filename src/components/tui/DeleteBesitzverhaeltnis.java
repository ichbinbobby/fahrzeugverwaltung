package components.tui;

import concepts.IFachkonzept;
import models.BesitzerMeta;
import models.FahrzeugMeta;
import utils.Console;

import java.util.Scanner;
import java.util.stream.Stream;

public class DeleteBesitzverhaeltnis extends AbstractMenu {
    private int tmpBesitzerId;

    DeleteBesitzverhaeltnis(IFachkonzept fachkonzept){
        this.fachkonzept = fachkonzept;
        boolean run = true;
        while (run) {
            showMenuInfo();
            showReachableMenus();
            run = getUserChoice();
        }
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
        showMenuInfo();
        System.out.println(separatorLine);
        this.fachkonzept.getFahrzeugeByBesitzer(this.tmpBesitzerId).forEach(currFahrzeug -> {
            System.out.println(currFahrzeug.getFahrzeugId() + ":  " + currFahrzeug.getBezeichnung());
        });
        System.out.println(separatorLine);
    }

    private boolean chooseBesitzer(){
        String separatorLine = "-".repeat(24);
        System.out.println(" 1/2 Besitzer auswählen");
        System.out.println(separatorLine);
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(currBesitzer.getBesitzerId() + ":  " + currBesitzer.getName());
        });
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.println("Besitzer auswählen\n(0 = Abbrechen)");
        System.out.print("> ");
        choice = input.nextInt();

        if (choice > 0){
            this.tmpBesitzerId = choice;
            return false;
        } else {
            showMenuInfo();
            System.out.println("Ein Fehler ist aufgetreten.");
            return true;
        }
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.print("Fahrzeug auswählen\n(0 = Zurück)\n> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            if (this.fachkonzept.setNewBesitzer(choice, -1)) {
                System.out.print("Löschen erfolgreich.\nDrücke ENTER zum fortfahren.");
                input.next();
                return false;
            } else {
                System.out.print("Es gab einen Fehler.\nTaste drücken zum fortfahren.");
                input.next();
                return true;
            }
        } else return choice != 0;
    }
}

