package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class ViewBesitzer extends MainMenu {

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
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(currBesitzer.getBesitzerId() + ":  " + currBesitzer.getName());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        System.out.println("Mehr Informationen zu\nBesitzer (0 = Zurück)");
        int choice = Console.inputInt();
        if (choice != 0){
            new ExtViewBesitzer(fachkonzept, choice).showMenu();
            return true;
        } else return choice != 0;
    }
}
