package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class ExtViewBesitzer extends AbstractMenu {

    ExtViewBesitzer(IFachkonzept fachkonzept, int besitzerId){
        this.fachkonzept = fachkonzept;
        boolean run = true;
        while (run){
            showMenuInfo();
            showReachableMenus(besitzerId);
            run = getUserChoice();
        }
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzer Informationen");
    }

    public void showReachableMenus(int besitzerId) {
        String separatorLine = "-".repeat(22);
        String name = this.fachkonzept.getBesitzerDetails(besitzerId).getName();

        System.out.println(separatorLine);
        System.out.println("Name: "+ name);
        System.out.println("Fahrzeuge:");
        this.fachkonzept.getFahrzeugeByBesitzer(besitzerId).forEach(fahrzeug ->{
            System.out.println("\t" + fahrzeug.getBezeichnung());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);

        System.out.println("(Leer/0 = Zurück)");
        while (true){
            System.out.print("> ");
            String choice = input.nextLine().trim();
            if (choice.isBlank() || choice.equals("0")) {
                return false;
            }
        }

    }
}
