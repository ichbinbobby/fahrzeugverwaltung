package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class DeleteBesitzverhaeltnis extends MainMenu {
    private int tmpBesitzerId;
    private boolean abbrechen = false;

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
        System.out.println(separatorLine);
        System.out.println("Besitzer auswählen\n(0 = Abbrechen)");
        System.out.print("> ");
        choice = input.nextInt();

        if (choice > 0){
            this.tmpBesitzerId = choice;
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
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.print("Fahrzeug auswählen\n(0 = Zurück)\n> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice > 0){
            if (this.fachkonzept.setNewBesitzer(choice, -1)) {
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

