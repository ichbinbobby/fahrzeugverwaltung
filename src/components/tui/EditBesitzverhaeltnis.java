package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class EditBesitzverhaeltnis extends MainMenu {
    private int tmpBesitzerId;
    private int tmpFahrzeugId;

    EditBesitzverhaeltnis(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzverhältnis bearbeiten");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(27);
        boolean run = true;
        System.out.println(separatorLine);
        while (run){
            run = chooseBesitzer();
        }
        showMenuInfo();
        System.out.println(separatorLine);
        run = true;
        while (run){
            run = chooseFahrzeug();
        }
        showMenuInfo();
        System.out.println(separatorLine);
    }

    private boolean chooseBesitzer(){
        String separatorLine = "-".repeat(23);
        System.out.println(" 1/2 Besitzer auswählen");
        System.out.println(separatorLine);
        this.fachkonzept.getAllBesitzer().forEach(currBesitzer -> {
            System.out.println(currBesitzer.getBesitzerId() + ":  " + currBesitzer.getName());
        });
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.println(separatorLine);
        System.out.println("Besitzer auswählen\n(0 = Abbrechen)");
        System.out.print("> ");
        choice = input.nextInt();

        if (choice > 0){
            this.tmpBesitzerId = choice;
            return false;
        } else {
            showMenuInfo();
            System.out.println("Ein Fehler ist aufgetreten.");
            return true;
        }
    }

    private boolean chooseFahrzeug(){
        String separatorLine = "-".repeat(23);
        System.out.println(" 2/2 Fahrzeug auswählen");
        System.out.println(separatorLine);
        this.fachkonzept.getAllFahrzeuge().forEach(currFahrzeug -> {
            int fahrzeugId = currFahrzeug.getFahrzeugId();
            System.out.println(fahrzeugId + ":  " + currFahrzeug.getBezeichnung() + " (" + this.fachkonzept.getBeistzerByFahrzeug(fahrzeugId).getName() + ")");
        });
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.println(separatorLine);
        System.out.println("Fahrzeug auswählen\n(0 = Abbrechen)");
        System.out.print("> ");
        choice = input.nextInt();

        if (choice > 0){
            this.tmpFahrzeugId = choice;
            return false;
        } else {
            showMenuInfo();
            System.out.println("Ein Fehler ist aufgetreten.");
            Console.pressEnterToContinue();
            return true;
        }
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner((System.in));

        System.out.println(
                "Das Fahrzeug '" +
                this.fachkonzept.getFahrzeugDetails(this.tmpFahrzeugId).getBezeichnung() +
                "' '" + this.fachkonzept.getBesitzerDetails(this.tmpBesitzerId).getName() +
                "' zuordnen?\n(j/N)"
        );
        String choice = input.next();
        if (choice.equals("j") || choice.equals("J") || choice.equals("ja") || choice.equals("Ja")){
            this.fachkonzept.setNewBesitzer(this.tmpFahrzeugId, this.tmpBesitzerId);
            System.out.println("Wurde gespeichert.");
            Console.pressEnterToContinue();
            return false;
        } else if (choice.equals("n") || choice.equals("N") || choice.equals("nein") || choice.equals("Nein") || choice.isBlank()){
            System.out.println("Abgebrochen");
            Console.pressEnterToContinue();
            return true;
        } else return true;

    }
}
