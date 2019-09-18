package components.gui;

import javax.swing.*;

public class UserInterface {
    public static void main(String[] args) {
        JFrame f = new JFrame();//creating instance of JFrame

        JTextField besitzer = new JTextField();
        JTextField fahrzeug = new JTextField();

        besitzer.setBounds(x: 50, y: 20, width: 100, height: 30);

        JButton b = new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}
