package components.gui;

import javax.swing.*;
import java.awt.*;
import java.lang.management.BufferPoolMXBean;

public class UserInterface {
    public static void main(String[] args) {
        JFrame f = new JFrame();//creating instance of JFrame
        JPanel gui = new JPanel();
        gui.setLayout(new GridLayout(0,2));

        JPanel besitzerPanel = new JPanel();
        JPanel fahrzeugPanel = new JPanel();
        besitzerPanel.setLayout(new BoxLayout(besitzerPanel, BoxLayout.PAGE_AXIS));
        fahrzeugPanel.setLayout(new BoxLayout(fahrzeugPanel, BoxLayout.PAGE_AXIS));

        JTextField besitzerTextField = new JTextField("Besitzer...");
        JTextField fahrzeugTextField = new JTextField("Fahrzeug...");
        JButton addBesitzerBtn = new JButton("Hinzufügen");
        JButton addFahrzeugBtn = new JButton("Hinzufügen");

        int width = 400;
        int height = 600;
        //int leftGap = (int) (width*0.2);
        //int rightGap= (int) (width*0.8);

        besitzerTextField.setBounds(20,50,150,50);
        fahrzeugTextField.setBounds(230,50,150,50);
        addBesitzerBtn.setBounds(20,150,100,40);
        addFahrzeugBtn.setBounds(230,150,100,40);

        System.out.println(besitzerTextField.getX());
        System.out.println(besitzerTextField.getY());

        gui.add(besitzerPanel);
        gui.add(fahrzeugPanel);
        besitzerPanel.add(besitzerTextField);
        besitzerPanel.add(addBesitzerBtn);
        fahrzeugPanel.add(fahrzeugTextField);
        fahrzeugPanel.add(addFahrzeugBtn);

        f.add(gui);
        f.setTitle("Fahrzeugverwaltung");
        f.setSize(width,height);//400 width and 500 height
        f.setVisible(true);//making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
