package components.tui;

import java.util.ArrayList;

//abstract Menu
public abstract class Menu {
    ArrayList<String> reachableMenus = new ArrayList<String>();

    public abstract void showMenuInfo();

    public abstract void showReachableMenus();

    public abstract void getUserChoice();
}
