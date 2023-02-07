package test.testActionListener;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class testComponent extends JPanel implements ActionListener {
    JButton b1;

    public testComponent() {
        super(new BorderLayout());
        b1 = new JButton("Click");
        b1.setSize(100, 30);
        b1.addActionListener(this);
        setSize(100, 30);
        add(b1, BorderLayout.CENTER);
        setVisible(true);
    }

    public void addActionListener(ActionListener listener) {
        b1.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Test Component");
        // if (b1.getText().equals("Click")) {
        //     b1.setText("ON");
        // } else {
        //     b1.setText("Click");
        // }
    }
}
