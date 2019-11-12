package components.gui;

import models.BesitzerMeta;
import models.FahrzeugMeta;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.lang.reflect.Proxy;
import java.util.function.Function;

public class GraphicalUserInterface {
    private JPanel mainPanel;
    private JPanel besitzerPanel;
    private JPanel fahrzeugPanel;
    private JTextField addBesitzerTextField;
    private JTextField addFahrzeugTextField;
    private JButton addBesitzerBtn;
    private JButton addFahrzeugBtn;
    private JButton deleteBesitzerBtn;
    private JButton deleteFahrzeugBtn;
    private JScrollPane besitzerScrollPane;
    private JScrollPane fahrzeugScrollPane;

    public GraphicalUserInterface() {
        addBesitzerBtn.addActionListener(e -> System.out.println(addBesitzerTextField.getText()));
        addFahrzeugBtn.addActionListener(e -> System.out.println(addFahrzeugTextField.getText()));
        deleteBesitzerBtn.addActionListener(e -> System.out.println(((JList<BesitzerMeta>) besitzerScrollPane.getViewport().getView()).getSelectedValue().getBesitzerId()));
        deleteFahrzeugBtn.addActionListener(e -> System.out.println(((JList<FahrzeugMeta>) fahrzeugScrollPane.getViewport().getView()).getSelectedValue().getFahrzeugId()));
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        GraphicalUserInterface gui = new GraphicalUserInterface();

        DefaultListModel<BesitzerMeta> l1 = new DefaultListModel<>();
        l1.addElement(new BesitzerMeta(1, "Sebastian"));
        l1.addElement(new BesitzerMeta(2, "Mark"));
        l1.addElement(new BesitzerMeta(3, "Angela"));
        l1.addElement(new BesitzerMeta(4, "Greta"));
        JList<BesitzerMeta> besitzerList = new JList<>(l1);
        SetValueCallback(BesitzerMeta.class, besitzerList, BesitzerMeta::getName);

        gui.besitzerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        gui.besitzerScrollPane.setViewportView(besitzerList);

        DefaultListModel<FahrzeugMeta> l2 = new DefaultListModel<>();
        l2.addElement(new FahrzeugMeta(1, "Ferrari"));
        l2.addElement(new FahrzeugMeta(2, "Mercedes"));
        l2.addElement(new FahrzeugMeta(3, "Tiger"));
        l2.addElement(new FahrzeugMeta(4, "Tesla"));
        JList<FahrzeugMeta> fahrzeugList = new JList<>(l2);
        SetValueCallback(FahrzeugMeta.class, fahrzeugList, FahrzeugMeta::getBezeichnung);
        gui.fahrzeugScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        gui.fahrzeugScrollPane.setViewportView(fahrzeugList);

        f.setContentPane(gui.mainPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setTitle("Fahrzeugverwaltung");
        f.setSize(400,600);
        f.setVisible(true);
    }

    private static <T> void SetValueCallback(Class<T> tClass, @NotNull JList<T> besitzerList, Function<T, String> customToString) {
        ListCellRenderer<? super T> originalCellRenderer = besitzerList.getCellRenderer();
        besitzerList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            Object proxyInstance = Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{}, (proxy, method, args) -> {
                if (method.getName().equals("toString")) {
                    return customToString.apply(value);
                }

                return method.invoke(value, args);
            });

            return originalCellRenderer.getListCellRendererComponent(list, (T) proxyInstance, index, isSelected, cellHasFocus);

        });
    }
}
