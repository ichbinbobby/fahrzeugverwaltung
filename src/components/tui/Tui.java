package components.tui;

import concepts.FachkonzeptMock;


public class Tui {
    public static void main(String[] args){
        new Menu(new FachkonzeptMock());
    }
}
