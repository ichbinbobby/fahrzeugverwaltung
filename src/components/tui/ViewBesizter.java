package components.tui;

public class ViewBesizter extends Menu {

    @Override
    public void showMenuInfo() {
        System.out.println("Besitzter anzeigen\n");
        System.out.println("");
    }

    @Override
    public void showReachableMenus() {

    }

    @Override
    public void getUserChoice() {
        System.out.println("Mehr Informationen zu\nBesitzer (0 = )");
    }
}
