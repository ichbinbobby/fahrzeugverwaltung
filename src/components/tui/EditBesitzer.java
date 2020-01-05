package components.tui;

import concepts.IFachkonzept;
import models.Besitzer;
import utils.Console;

import java.util.Scanner;

public class EditBesitzer extends AbstractMenu {

    EditBesitzer(IFachkonzept fachkonzept){
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
        System.out.println("Besitzer bearbeiten");
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
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            Besitzer besitzer = this.fachkonzept.getBesitzerDetails(choice);
            System.out.println("Neuer Name des Besitzers "+ besitzer.getName() +":\n(Leer/0 = Zurück)");
            String name = input.nextLine().trim();

            if (name.isBlank() || name.equals("0")) {
                return false;
            } else {
                besitzer.setName(name);
                if (this.fachkonzept.saveBesitzer(besitzer) >= 0){
                    System.out.println("Saved");
                    return false;
                } else {
                    return false;
                }
            }
        } else return choice != 0;
    }
}
