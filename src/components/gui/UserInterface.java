package components.gui;

import javax.swing.*;

public class UserInterface {
    public static void main(String[] args) {
        JFrame f = new JFrame();//creating instance of JFrame
        JPanel gui = new JPanel();

        f.add(gui);

        JTextField besitzerTextField = new JTextField();
        JTextField fahrzeugTextField = new JTextField();

        besitzerTextField.setBounds(0,50,100, 40);
        fahrzeugTextField.setBounds((int) (f.getWidth()*0.8f),50, 100,40);

        gui.add(besitzerTextField);
        gui.add(fahrzeugTextField);

        //JButton b = new JButton("click");//creating instance of JButton
        //b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        //f.add(b);//adding button in JFrame

        f.setSize(1280,720);//400 width and 500 height
        //f.setLayout();//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}
