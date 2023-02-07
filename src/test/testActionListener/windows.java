package test.testActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

public class windows extends JFrame {
    testComponent element = new testComponent();

    public windows() {
        super();
        this.setLayout(new BorderLayout());
        element.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // JOptionPane.showMessageDialog(null, "Look Ma, I'm in Main...");
            }
        });
        this.add(element, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new windows();
    }
}
