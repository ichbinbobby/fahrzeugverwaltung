package components.tui;

import concepts.FachkonzeptMock;
import concepts.IFachkonzept;


public class Tui {
    Tui(IFachkonzept fachkonzept){
        new MainMenu(fachkonzept).showMenu();
    }
}
