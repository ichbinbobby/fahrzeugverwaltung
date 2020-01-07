package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    protected IFachkonzept fachkonzept;

    MainMenu(IFachkonzept fachkonzept) {
        this.fachkonzept = fachkonzept;
    }

    public void showMenu(){
        boolean run = true;
        while (run) {
            showMenuInfo();
            showReachableMenus();
            run = getUserChoice();
        }
    }

    public void showMenuInfo() {
        Console.clear();
        System.out.println("Hauptmenu");
    }
    public void showReachableMenus() {
        String separatorLine = "-".repeat(35);
        System.out.println(
                separatorLine +
                "\nBesitzer anzeigen              ( 1)\n" +
                "Besitzer hinzufügen            ( 2)\n" +
                "Besitzer bearbeiten            ( 3)\n" +
                "Besitzer löschen               ( 4)\n" +
                separatorLine +
                "\nFahrzeuge anzeigen             ( 5)\n" +
                "Fahrzeug hinzufügen            ( 6)\n" +
                "Fahrzeug bearbeiten            ( 7)\n" +
                "Fahrzeug löschen               ( 8)\n" +
                separatorLine +
                "\nBesitzverhältnisse anzeigen    ( 9)\n" +
                "Besitzverhältnisse bearbeiten  (10)\n" +
                "Besitzverhältniss löschen      (11)\n" +
                separatorLine +
                "\nProgramm beenden               ( 0)"
        );


    }
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.print("> ");
        choice = input.nextInt();
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }

        switch (choice){
            case 0:
                return false;
            case 1:
                new ViewBesitzer(fachkonzept).showMenu();
                break;
            case 2:
                new AddBesitzer(fachkonzept).showMenu();
                break;
            case 3:
                new EditBesitzer(fachkonzept).showMenu();
                break;
            case 4:
                new DeleteBesitzer(fachkonzept).showMenu();
                break;
            case 5:
                new ViewFahrzeug(fachkonzept).showMenu();
                break;
            case 6:
                new AddFahrzeug(fachkonzept).showMenu();
                break;
            case 7:
                new EditFahrzeug(fachkonzept).showMenu();
                break;
            case 8:
                new DeleteFahrzeug(fachkonzept).showMenu();
                break;
            case 9:
                new ViewBesitzverhaeltnis(fachkonzept).showMenu();
                break;
            case 10:
                new EditBesitzverhaeltnis(fachkonzept).showMenu();
                break;
            case 11:
                new DeleteBesitzverhaeltnis(fachkonzept).showMenu();
                break;
        }
        return true;
    }
}
