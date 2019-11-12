package components.gui;

import models.BesitzerMeta;
import models.FahrzeugMeta;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.lang.reflect.Proxy;
import java.util.function.Function;

public class GraphicalUserInterface {
    private final DefaultListModel<BesitzerMeta> besitzerListModel;
    private final DefaultListModel<FahrzeugMeta> fahrzeugListModel;
    private final JList<BesitzerMeta> besitzerList;
    private final JList<FahrzeugMeta> fahrzeugList;
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
    private JButton verbindenButton;

    public GraphicalUserInterface() {
        this.besitzerListModel = new DefaultListModel<>();
        this.besitzerListModel.addElement(new BesitzerMeta(1, "Sebastian"));
        this.besitzerListModel.addElement(new BesitzerMeta(2, "Mark"));
        this.besitzerListModel.addElement(new BesitzerMeta(3, "Angela"));
        this.besitzerListModel.addElement(new BesitzerMeta(4, "Greta"));
        this.besitzerList = new JList<>(this.besitzerListModel);
        SetValueCallback(BesitzerMeta.class, besitzerList, BesitzerMeta::getName);

        this.besitzerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.besitzerScrollPane.setViewportView(besitzerList);

        this.fahrzeugListModel = new DefaultListModel<>();
        this.fahrzeugListModel.addElement(new FahrzeugMeta(1, "Ferrari"));
        this.fahrzeugListModel.addElement(new FahrzeugMeta(2, "Mercedes"));
        this.fahrzeugListModel.addElement(new FahrzeugMeta(3, "Tiger"));
        this.fahrzeugListModel.addElement(new FahrzeugMeta(4, "Tesla"));
        this.fahrzeugList = new JList<>(this.fahrzeugListModel);
        SetValueCallback(FahrzeugMeta.class, fahrzeugList, FahrzeugMeta::getBezeichnung);

        this.fahrzeugScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.fahrzeugScrollPane.setViewportView(fahrzeugList);
        addBesitzerBtn.addActionListener(e -> {
            String besitzer = addBesitzerTextField.getText();
            System.out.println(besitzer);
            this.besitzerListModel.addElement(new BesitzerMeta(-1, besitzer));
            addBesitzerTextField.setText("");
        });
        addFahrzeugBtn.addActionListener(e -> {
            String fahrzeug = addFahrzeugTextField.getText();
            System.out.println(fahrzeug);
            this.fahrzeugListModel.addElement(new FahrzeugMeta(-1, fahrzeug));
            addFahrzeugTextField.setText("");
        });
        deleteBesitzerBtn.addActionListener(e -> {
            int index = besitzerList.getSelectedIndex();
            BesitzerMeta value = besitzerList.getSelectedValue();
            if(value != null){
                System.out.println(value);
                deleteElement(this.besitzerListModel, index);
            }
        });
        deleteFahrzeugBtn.addActionListener(e -> {
            int index = fahrzeugList.getSelectedIndex();
            FahrzeugMeta value = fahrzeugList.getSelectedValue();
            if(value != null){
                System.out.println(value);
                deleteElement(this.fahrzeugListModel, index);
            }
        });
        verbindenButton.addActionListener(e -> {
            BesitzerMeta besitzer = besitzerList.getSelectedValue();
            FahrzeugMeta fahrzeug = fahrzeugList.getSelectedValue();
            if( besitzer == null || fahrzeug == null ) {
                System.out.println("Es müssen beide ausgewählt werden.");
            } else {
                System.out.println("Verbinde Besitzer " + besitzer + " mit Fahrzeug " + fahrzeug);
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        GraphicalUserInterface gui = new GraphicalUserInterface();

        f.setContentPane(gui.mainPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setTitle("Fahrzeugverwaltung");
        f.setSize(400,600);
        f.setVisible(true);
    }

    private <T> void deleteElement(DefaultListModel<T> list, int index) {
        list.remove(index);
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
