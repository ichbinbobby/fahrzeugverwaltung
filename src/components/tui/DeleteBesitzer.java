package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class DeleteBesitzer extends AbstractMenu {
    private IFachkonzept fachkonzept;

    DeleteBesitzer(IFachkonzept fachkonzept){
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
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.println("Besitzer auswählen\n(0 = Zurück)");
        System.out.print("> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            if (this.fachkonzept.deleteBesitzer(choice)) {
                System.out.print("Löschen erfolgreich.\nDrücke ENTER zum fortfahren.");
                input.next();
                return false;
            } else {
                System.out.println("Es gab einen Fehler.\nTaste drücken zum fortfahren.");
                input.next();
                return true;
            }
        } else return choice != 0;
    }
}
