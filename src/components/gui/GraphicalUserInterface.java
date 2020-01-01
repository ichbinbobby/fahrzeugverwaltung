package components.gui;

import sql.Delete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalUserInterface {
    private final DefaultListModel<String> besitzerListModel;
    private final DefaultListModel<String> fahrzeugListModel;
    private final JList<String> besitzerList;
    private final JList<String> fahrzeugList;
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
        this.besitzerListModel.addElement("Sebastian");
        this.besitzerListModel.addElement("Mark");
        this.besitzerListModel.addElement("Angela");
        this.besitzerListModel.addElement("Greta");
        this.besitzerList = new JList<>(this.besitzerListModel);
        this.besitzerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.besitzerScrollPane.setViewportView(besitzerList);

        this.fahrzeugListModel = new DefaultListModel<>();
        this.fahrzeugListModel.addElement("Ferrari");
        this.fahrzeugListModel.addElement("Mercedes");
        this.fahrzeugListModel.addElement("Tiger");
        this.fahrzeugListModel.addElement("Tesla");
        this.fahrzeugList = new JList<>(this.fahrzeugListModel);
        this.fahrzeugScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.fahrzeugScrollPane.setViewportView(fahrzeugList);

        Delete d = new Delete();

        addBesitzerBtn.addActionListener(e -> {
            String besitzer = addBesitzerTextField.getText();
            System.out.println(besitzer);
            addElement(this.besitzerListModel, besitzer);
            addBesitzerTextField.setText("");
        });
        addFahrzeugBtn.addActionListener(e -> {
            String fahrzeug = addFahrzeugTextField.getText();
            System.out.println(fahrzeug);
            addElement(this.fahrzeugListModel, fahrzeug);
            addFahrzeugTextField.setText("");
        });
        deleteBesitzerBtn.addActionListener(e -> {
            int index = besitzerList.getSelectedIndex();
            String value = besitzerList.getSelectedValue();
            if(value != null){
                System.out.println(value);
                deleteElement(this.besitzerListModel, index);
                d.deleteBesitzer(index);
            }
        });
        deleteFahrzeugBtn.addActionListener(e -> {
            int index = fahrzeugList.getSelectedIndex();
            String value = fahrzeugList.getSelectedValue();
            if(value != null){
                System.out.println(value);
                deleteElement(this.fahrzeugListModel, index);
                d.deleteFahrzeug(index);
            }
        });
        verbindenButton.addActionListener(e -> {
            String besitzer = besitzerList.getSelectedValue();
            String fahrzeug = fahrzeugList.getSelectedValue();
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

    private <T> void addElement(DefaultListModel<T> list, T element) {
        list.addElement(element);
    }

    private <T> void deleteElement(DefaultListModel<T> list, int index) {
        list.remove(index);
    }
}
