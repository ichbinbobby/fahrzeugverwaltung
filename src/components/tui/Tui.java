package components.tui;

import concepts.IFachkonzept;


public class Tui {
    public Tui(IFachkonzept fachkonzept) {
        new MainMenu(fachkonzept).showMenu();
    }
}
