package components;

import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Notepad {
    // JFrame frame;
    MyFrame frame;
    public MenubarCustom menuBar;
    MainArea mainArea;
    FileOperation fileOperation;
    JLabel statusBar;

    public Notepad() {
        frame = new MyFrame();
        frame.setLayout(new BorderLayout());
        menuBar = new MenubarCustom(this);
        menuBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // System.out.println("TEST");
            }
        });

        mainArea = new MainArea(this);
        mainArea.addKeyListener(new KeyAdapter() {
        });

        statusBar = new JLabel("Status Bar", SwingConstants.CENTER);

        frame.Add(menuBar, BorderLayout.NORTH);
        frame.Add(mainArea, BorderLayout.CENTER);

        JScrollPane tempArea = new JScrollPane(mainArea);

        tempArea.setBorder(BorderFactory.createEmptyBorder());
        tempArea.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        // tempArea.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        frame.Add(tempArea, BorderLayout.CENTER);
        frame.Add(new JLabel("  "), BorderLayout.WEST);
        frame.Add(new JLabel("  "), BorderLayout.EAST);

        frame.Add(statusBar, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.out.println("Closing main window");
                System.out.println(fileOperation.isChanged());

                if (fileOperation.isChanged()) {
                    int userSelected = JOptionPane.showConfirmDialog(frame, "Do you want to save?", "Save file...",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (userSelected == JOptionPane.YES_OPTION) {
                        fileOperation.Save();
                        frame.dispose();
                    } else if (userSelected == JOptionPane.NO_OPTION) {
                        // Do nothing
                        frame.dispose();
                    }
                } else {
                    frame.dispose();
                }
            }

        });
        // frame.setUndecorated(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2,
                dimension.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);
    }

    public void handleClose() {
        if (fileOperation.isChanged()) {
            int userSelected = JOptionPane.showConfirmDialog(frame, JOptionPane.YES_NO_OPTION);
            if (userSelected == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        }
    }

    public void closeNotepad() {
        System.out.println("Close");
        frame.dispose();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Notepad();
            }
        });
    }
}
