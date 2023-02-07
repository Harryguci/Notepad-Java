package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.io.File;
import components.ReadFile;

public class openFile extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu file;
    JMenuItem open;

    public openFile() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        menuBar.add(file);
        menuBar.setBounds(0, 0, 1000, 30);
        this.add(menuBar);

        this.setBounds(100, 100, 300, 400);
        this.setLayout(null);
        this.setVisible(true);
    }

    public String showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        String filePath = "";
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            filePath = selectedFile.getAbsolutePath();

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        return filePath;
    }

    public static void main(String[] args) {
        openFile frame = new openFile();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == open) {
            System.out.println("Show file chooser");
            String filePath = showFileChooser();
            ReadFile reader = new ReadFile(filePath);

            // Sending file data to MainArea.textArea

            
        } else {
            System.out.println("Can not read the file");
        }

    }
}
