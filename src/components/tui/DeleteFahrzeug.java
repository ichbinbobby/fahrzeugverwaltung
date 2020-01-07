package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class DeleteFahrzeug extends AbstractMenu {

    DeleteFahrzeug(IFachkonzept fachkonzept){
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
        System.out.println("Fahrzeug löschen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(16);
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
        System.out.print("Fahrzeug auswählen\n(0 = Zurück)\n> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            if (this.fachkonzept.deleteFahrzeug(choice)) {
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
