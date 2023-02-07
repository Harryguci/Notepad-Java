package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.awt.image.BufferedImage;

class MyFrame extends JFrame implements ActionListener {

    // Create a frame with a button.
    public MyFrame() {
        super("A window");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
    }

    public void Add(Component components, Object constraints) {
        Container contentPane = getContentPane();
        try {
            if (constraints != null)
                contentPane.add(components, constraints);
            else
                contentPane.add(components);

        } catch (Exception err) {
            System.out.println("MyFrame : Cannot add components");
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyFrame frame = new MyFrame();

                frame.setUndecorated(true);
                frame.setSize(new Dimension(300, 500));
                frame.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
    }
}