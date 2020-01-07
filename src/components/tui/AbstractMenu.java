package components.tui;

import concepts.IFachkonzept;

abstract public class AbstractMenu {
    protected IFachkonzept fachkonzept;
    abstract public void showMenuInfo();
    abstract public void showReachableMenus();
    abstract public boolean getUserChoice();
}
