package components.tui;

import concepts.IFachkonzept;
import models.Besitzer;
import utils.Console;

import java.util.Scanner;

public class EditBesitzer extends MainMenu {

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
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(currBesitzer.getBesitzerId() + ":  " + currBesitzer.getName());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.print("Besitzer auswählen\n(0 = Zurück)\n> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            Besitzer besitzer = this.fachkonzept.getBesitzerDetails(choice);
            System.out.print("Neuer Name des Besitzers "+ besitzer.getName() +":\n(Leer/0 = Abbrechen)\n> ");
            String name = input.next().trim();

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