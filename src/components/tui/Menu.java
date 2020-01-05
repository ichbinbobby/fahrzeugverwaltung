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
                "Besitzverhältniss hinzufügen   (10)\n" +
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
                new ViewBesitzer(fachkonzept);
                break;
            case 2:
                new AddBesitzer(fachkonzept);
                break;
            case 3:
                new EditBesitzer(fachkonzept);
                break;
            case 4:
                new DeleteBesitzer(fachkonzept);
                break;
            case 5:
                new ViewFahrzeug(fachkonzept);
                break;
            case 6:
                new AddFahrzeug(fachkonzept);
                break;
            case 7:
                new EditFahrzeug(fachkonzept);
                break;
            case 8:
                new DeleteFahrzeug(fachkonzept);
                break;
            case 9:
                new ViewBesitzverhaeltniss(fachkonzept);
                break;
            case 10:
                new AddBesitzverhaeltniss(fachkonzept);
                break;
            case 11:
                new DeleteBesitzverhaeltniss(fachkonzept);
                break;
        }
        return true;
    }
}
