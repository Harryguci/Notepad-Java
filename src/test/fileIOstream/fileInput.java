package test.fileIOstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.event.KeyEvent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class fileInput {
    File fileRef;
    JFileChooser chooser;
    JFrame frame;
    JTextArea textArea;

    public fileInput(JFrame frame, JTextArea textArea) {
        this.frame = frame;
        this.textArea = textArea;
        fileRef = null;
        chooser = new JFileChooser(new File("."));
        chooser.setApproveButtonMnemonic(KeyEvent.VK_O);
        chooser.setDialogTitle("Open file...");
        chooser.setApproveButtonText("Open this");
    }

    public void open() {
        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                fileRef = chooser.getSelectedFile();
                FileInputStream fin = new FileInputStream(fileRef);
                int i;

                this.textArea.setText("");
                while ((i = fin.read()) != -1) {
                    System.out.print((char) i);
                    this.textArea.append(String.valueOf((char) i));
                }
                fin.close();
            } catch (IOException err) {
                // TODO: handle exception

                System.out.println("ERROR: Can not Read file");
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        JTextArea textArea = new JTextArea(30, 30);
        frame.setSize(800, 600);
        frame.add(textArea);

        frame.pack();
        frame.setVisible(true);
        fileInput demo = new fileInput(frame, textArea);
        demo.open();
    }
}
