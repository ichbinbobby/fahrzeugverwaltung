package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends AbstractMenu{
    private IFachkonzept fachkonzept;
    ArrayList<String> menus = new ArrayList<String>();

    Menu(IFachkonzept fachkonzept) {
        this.fachkonzept = fachkonzept;
        boolean run = true;
        while (run) {
            showMenuInfo();
            showReachableMenus();
            run = getUserChoice();
        }
    };
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Hauptmenu\n");
    };
    public void showReachableMenus() {
        String separatorLine = "-".repeat(35);
        System.out.println(
                separatorLine +
                "Besitzer anzeigen              ( 1)\n" +
                "Besitzer hinzufügen            ( 2)\n" +
                "Besitzer bearbeiten            ( 3)\n" +
                "Besitzer löschen               ( 4)\n" +
                separatorLine +
                "Fahrzeuge anzeigen             ( 5)\n" +
                "Fahrzeug hinzufügen            ( 6)\n" +
                "Fahrzeug bearbeiten            ( 7)\n" +
                "Fahrzeug löschen               ( 8)\n" +
                separatorLine +
                "Besitzverhältnisse anzeigen    ( 9)\n" +
                "Besitzverhältniss hinzufügen   (10)\n" +
                "Besitzverhältniss löschen      (11)\n" +
                separatorLine +
                "Programm beenden               ( 0)\n"
        );


    };
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("> ");
        while (!input.hasNextInt()){
            System.out.println("Keine gültige Eingabe");
            choice = input.nextInt() - 1;
        }

        switch (choice){
            case 0:
                return false;
            case 1:
                new ViewBesizter(fachkonzept);
            case 2:
                new AddBesitzer(fachkonzept);
            case 3:
                new EditBesitzer(fachkonzept);
            case 4:
                new DeleteBesitzer(fachkonzept);
            case 5:
                new ViewFahrzeug(fachkonzept);
            case 6:
                new AddFahrzeug(fachkonzept);
            case 7:
                new EditFahrzeug(fachkonzept);
            case 8:
                new DeleteFahrzeug(fachkonzept);
            case 9:
                new ViewBesitzverhaeltniss(fachkonzept);
            case 10:
                new AddBesitzverhaeltniss(fachkonzept);
            case 11:
                new DeleteBesitzverhaeltniss(fachkonzept);
        }
        return true;
    };
}
