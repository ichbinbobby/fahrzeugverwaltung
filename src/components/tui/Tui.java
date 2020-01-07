package components.tui;

import concepts.FachkonzeptMock;
import concepts.IFachkonzept;


public class Tui {
    public static void main(String[] args){
        new MainMenu(new FachkonzeptMock()).showMenu();
    }
    Tui(IFachkonzept fachkonzept){
        new MainMenu(fachkonzept).showMenu();
    }
}
