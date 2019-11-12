package components.tui;
import concepts.IFachkonzept;
import models.BesitzerMeta;

import java.util.Scanner;

public class Tui {
    public static void main(String[] args){
        mainMenu();
    }
//    public static void mainMenu(IFachkonzept concept) {
    public static void mainMenu() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "Fahrzeugbesitzerverwaltungssystem\n" +
                    "---------------------------------\n" +
                    "Besitzer anzeigen            ( 1)\n" +
                    "Besitzer hinzufügen          ( 2)\n" +
                    "Besitzer bearbeiten          ( 3)\n" +
                    "Besitzer löschen             ( 4)\n" +
                    "---------------------------------\n" +
                    "Fahrzeuge anzeigen           ( 5)\n" +
                    "Fahrzeuge hinzufügen         ( 6)\n" +
                    "Fahrzeuge bearbeiten         ( 7)\n" +
                    "Fahrzeuge löschen            ( 8)\n" +
                    "---------------------------------\n" +
                    "Bestizverhältnisse anzeigen  ( 9)\n" +
                    "Besitzverhältnis hinzufügen  (10)\n" +
                    "Besitzverhältnis löschen     (11)\n" +
                    "---------------------------------\n" +
                    "Programm beenden             ( 0)\n"
            );

            System.out.print("> ");
            int selection = input.nextInt();

            switch (selection) {
                case 0:
                    input.close();
                    return;
                case 1:
//                    viewUser(concept, input);
                    viewUser(input);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        }
    }

//    private static void viewUser(IFachkonzept concept, Scanner input){
    private static void viewUser(Scanner input){
        BesitzerMeta user1 = new BesitzerMeta();
        user1.setName("Greta");
        BesitzerMeta user2 = new BesitzerMeta();
        user2.setName("Sebastian");
        BesitzerMeta[] users = {user1, user2};
//        BesitzerMeta[] users = concept.getAllBesitzer();
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < users.length; i++) {
            outputString.append(users[i].getName()).append("\t").append(i+1).append("\n");
        }
        while (true) {
            System.out.print(
                    "Besitzer anzeigen\n" +
                    "------------------------\n" +
                    outputString +
                    "------------------------\n" +
                    "Mehr Informationen zu\n" +
                    "Besitzer (0 = Zurück)\n\n"
            );
            System.out.print("> ");
            int selection = input.nextInt();

            switch (selection){
                case 0:
                    return;
                default:
                    viewUserExt(input, selection);
                    break;
            }
        }
    }
    private static void viewUserExt(Scanner input, int oldSelection){
        String name = "Greta";

        while (true) {
            System.out.print(
                    "Besitzer Informationen\n" +
                    "------------------------\n" +
                    "Name: " + name + "\n" +
                    "Fahrzeuge: \n" +
                    "------------------------\n" +
                    "(Leer/0 = Zurück)"
            );
            System.out.println("> ");
            int selection = input.nextInt();
        }
    }
    private void viewVehicle(){
        System.out.println("ViewVehicle");
        return;
    }
    private void viewRelations(){}

}
