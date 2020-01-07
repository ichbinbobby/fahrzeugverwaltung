package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class ViewFahrzeug extends MainMenu {

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
        this.fachkonzept.getAllFahrzeuge().forEach(currFahrzeug -> {
            System.out.println(currFahrzeug.getFahrzeugId() + ":  " + currFahrzeug.getBezeichnung());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.print("Mehr Informationen zu\nFahrzeug (0 = Zurück)\n> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            new ExtViewFahrzeug(fachkonzept, choice).showMenu();
            return true;
        } else return choice != 0;


    }
}