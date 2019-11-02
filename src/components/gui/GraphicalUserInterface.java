package components.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton verbindenButton;

    public GraphicalUserInterface() {
        addBesitzerBtn.addActionListener(e -> {
            String besitzer = addBesitzerTextField.getText();
            System.out.println(besitzer);
            addScrollPaneElement(besitzerScrollPane, besitzer);
            addBesitzerTextField.setText("");
        });
        addFahrzeugBtn.addActionListener(e -> {
            String fahrzeug = addFahrzeugTextField.getText();
            System.out.println(fahrzeug);
            addScrollPaneElement(fahrzeugScrollPane, fahrzeug);
            addFahrzeugTextField.setText("");
        });
        deleteBesitzerBtn.addActionListener(e -> System.out.println(((JList<String>)besitzerScrollPane.getViewport().getView()).getSelectedValue()));
        deleteFahrzeugBtn.addActionListener(e -> System.out.println(((JList<String>)fahrzeugScrollPane.getViewport().getView()).getSelectedValue()));
        verbindenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String besitzer;
                besitzer = ((JList<String>)besitzerScrollPane.getViewport().getView()).getSelectedValue();
                String fahrzeug;
                fahrzeug = ((JList<String>)fahrzeugScrollPane.getViewport().getView()).getSelectedValue();
                if( besitzer == null || fahrzeug == null ) {
                    System.out.println("Es müssen beide ausgewählt werden.");
                } else {
                    System.out.println("Verbinde Besitzer " + besitzer + " mit Fahrzeug " + fahrzeug);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        GraphicalUserInterface gui = new GraphicalUserInterface();

        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Sebastian");
        l1.addElement("Mark");
        l1.addElement("Angela");
        l1.addElement("Greta");
        JList<String> besitzerList = new JList<>(l1);
        gui.besitzerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        gui.besitzerScrollPane.setViewportView(besitzerList);

        DefaultListModel<String> l2 = new DefaultListModel<>();
        l2.addElement("Ferrari");
        l2.addElement("Mercedes");
        l2.addElement("Tiger");
        l2.addElement("Tesla");
        JList<String> fahrzeugList = new JList<>(l2);
        gui.fahrzeugScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        gui.fahrzeugScrollPane.setViewportView(fahrzeugList);

        f.setContentPane(gui.mainPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setTitle("Fahrzeugverwaltung");
        f.setSize(400,600);
        f.setVisible(true);
    }

    private void addScrollPaneElement(JScrollPane pane, String element) {
        (
                (DefaultListModel<String>) (
                        (JList<String>) pane
                                .getViewport()
                                .getView()
                ).getModel()
        ).addElement(element);
    }
}
