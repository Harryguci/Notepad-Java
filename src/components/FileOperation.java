package components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.KeyEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileOperation {
    Notepad notepad;
    JFileChooser chooser;
    String fileName, applicationName;
    File fileReference;

    private boolean fileSaved, fileChanged;

    public FileOperation(Notepad notepad) {
        fileName = "Default.txt";
        applicationName = "Harryguci Note";
        fileSaved = false;
        fileChanged = true;

        this.notepad = notepad;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        this.notepad.frame.setTitle(fileName + " - " + applicationName);
    }

    public void setChanged(boolean value) {
        this.fileChanged = value;
    }

    public boolean isChanged() {
        return fileChanged;
    }

    public void newFile() {
        fileName = "Default.txt";
        applicationName = "Harryguci Note";
        fileSaved = false;
        fileChanged = true;
        this.notepad.frame.setTitle(fileName + " - " + applicationName);
        fileReference = null;
    }

    public boolean OpenFile(File selectedFile) {
        try {
            FileInputStream fin = new FileInputStream(selectedFile);
            fileName = selectedFile.getName();
            this.notepad.frame.setTitle(fileName + " - " + applicationName);
            int i;

            this.notepad.mainArea.setText("");
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
                this.notepad.mainArea.appendContent(String.valueOf((char) i));
            }
            fin.close();

            fileSaved = true;
            fileChanged = false;

            fileReference = selectedFile;
            return true;
        } catch (Exception err) {
            System.out.println("Error: FileOperation.OpenFile()");
        }
        return false;
    }

    public void Open() {
        chooser.setDialogTitle("Open file");
        chooser.setApproveButtonMnemonic(KeyEvent.VK_O);
        chooser.setApproveButtonText("Open this");
        chooser.setToolTipText("Click me to open selected file");
        File temp = null;

        do {
            if (chooser.showOpenDialog(this.notepad.frame) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            temp = chooser.getSelectedFile();
            if (temp.exists()) {
                break;
            }

            JOptionPane.showMessageDialog(this.notepad.frame, "File is not exist");
        } while (true);

        if (this.OpenFile(temp) == false) {
            JOptionPane.showMessageDialog(this.notepad.frame, "Can not open file, please try again");
        }
    }

    public boolean isSaved() {
        return fileSaved;
    }

    public boolean SaveAs() {
        chooser.setDialogTitle("Save As...");
        int userSelection = chooser.showSaveDialog(this.notepad.frame);
        if (userSelection == JFileChooser.CANCEL_OPTION) {
            return false;
        }
        File temp = chooser.getSelectedFile();

        fileReference = temp;
        // System.out.println("Pending: Save as...");
        // System.out.println(fileReference.getAbsolutePath());
        String content = this.notepad.mainArea.getText();

        try {
            FileOutputStream fout = new FileOutputStream(fileReference);

            if (content.equals("")) {
                JOptionPane.showMessageDialog(this.notepad.frame, "File is empty!");
            } else {
                byte b[] = content.getBytes();// converting string into byte array
                fout.write(b);
                JOptionPane.showMessageDialog(this.notepad.frame, "Saving is successful");
            }
            fout.close();
            fileChanged = false;
            fileSaved = true;
            fileReference = temp;
            fileName = temp.getName();

            this.notepad.frame.setTitle(fileName + " - " + applicationName);
            this.notepad.frame.setVisible(true);

            return true;
        } catch (IOException err) {
            System.out.println("FileOperation: Can not Save as");
        }

        return false;
    }

    // Save current file.
    public boolean Save() {
        /*
         * Plan:
         * 1. Case file already exist. Save it.
         * 2. Case file is not exist. Call Save as (write later).
         */
        if (this.fileSaved) {
            try {
                FileOutputStream fout = new FileOutputStream(fileReference);

                // get content of Notepad then write to file(file reference)
                String content = this.notepad.mainArea.getText();
                if (content.equals("")) {
                    JOptionPane.showMessageDialog(this.notepad.frame, "File is empty!");
                    fout.close();
                } else {
                    byte b[] = content.getBytes();// converting string into byte array
                    fout.write(b);
                }
                fout.close();
                return true;
            } catch (IOException err) {
                return false;
            } finally {
                System.out.println("FileOperation: Save() is finished with existed File");
            }

        } else {
            // Call SaveAs() in here
            this.SaveAs();
            System.out.println("Calling Saving As func");
        }

        return false;
    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        FileOperation fileOperation = new FileOperation(notepad);
        // fileOperation.Open();

        fileOperation.SaveAs();
    }
}
