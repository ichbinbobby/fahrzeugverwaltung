package components.tui;

import concepts.IFachkonzept;

abstract public class AbstractMenu {
    protected IFachkonzept fachkonzept;
    public void showMenuInfo() {}
    public void showReachableMenus() {}
    public boolean getUserChoice() { return true;}
    }
