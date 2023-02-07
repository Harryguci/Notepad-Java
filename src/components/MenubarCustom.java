package components;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JDialog;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;

public class MenubarCustom extends JMenuBar implements ActionListener {
    protected ArrayList<JMenu> menuList = new ArrayList<>();
    protected JMenu font, help;
    protected JMenuItem menuItem_new, menuItem_open, menuItem_save, menuItem_saveAs,
            menuItem_color, menuItem_size, menuItem_style, menuItem_close, menuItem_help;
    protected File fileCurrent;
    public Notepad notepad;
    FileOperation fileOperation;

    public MenubarCustom(Notepad notepad) {
        super();
        init(notepad, 1000, 40);
    }

    public MenubarCustom(Notepad notepad, int width, int height) {
        super();
        init(notepad, width, height);
    }

    public void init(Notepad notepad, int width, int height) {
        this.notepad = notepad;
        fileCurrent = null;
        fileOperation = new FileOperation(notepad);
        this.notepad.fileOperation = fileOperation;

        setBounds(0, 0, width, height);

        menuList.add(new JMenu("File"));
        menuList.add(new JMenu("Edit"));
        menuList.add(new JMenu("Selection"));

        help = new JMenu("Help");
        menuItem_help = new JMenuItem("Help");
        help.add(menuItem_help);
        menuList.add(help);

        menuItem_new = new JMenuItem("New");
        menuItem_open = new JMenuItem("Open");
        menuItem_save = new JMenuItem("Save");
        menuItem_saveAs = new JMenuItem("Save As");
        menuItem_close = new JMenuItem("Close");

        menuList.get(0).add(menuItem_new);
        menuList.get(0).add(menuItem_open);
        menuList.get(0).add(menuItem_save);
        menuList.get(0).add(menuItem_saveAs);
        menuList.get(0).add(menuItem_close);

        font = new JMenu("Font");

        menuItem_color = new JMenuItem("Color");
        menuItem_size = new JMenuItem("Size");
        menuItem_style = new JMenuItem("Style");

        font.add(menuItem_color);
        font.add(menuItem_size);
        font.add(menuItem_style);

        menuList.get(1).add(font);

        for (int i = 0; i < menuList.size(); i++) {
            add(menuList.get(i));
        }

        setVisible(true);
    }

    public File getCurrentFile() {
        return fileCurrent;
    }

    public String showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        String filePath = "";
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileCurrent = selectedFile;

            filePath = selectedFile.getAbsolutePath();

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        return filePath;
    }

    public void showHelpDialog() {
        JDialog dialog = new JDialog(this.notepad.frame, "Help dialog", true);
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        dialog.setLayout(new FlowLayout());
        JLabel heading = new JLabel("Harryguci", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));

        jp.add(heading);
        jp.add(new JLabel("Hello my name is Harryguci. This is my application. It was built in 2023."));

        dialog.add(jp);
        dialog.pack();
        dialog.setSize(dialog.getWidth() + 150, dialog.getHeight());
        dialog.setLocationRelativeTo(this.notepad.frame);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        // JFrame frame = new JFrame("Frame demo");

        // frame.add(new MenubarCustom(1000, 30));
        // frame.setBounds(100, 100, 200, 300);
        // frame.setLayout(null);
        // frame.setVisible(true);
    }

    public void addActionListener(ActionListener listener) {
        System.out.println("Add action listener");

        menuItem_new.addActionListener(listener);
        menuItem_open.addActionListener(listener);
        menuItem_save.addActionListener(listener);
        menuItem_saveAs.addActionListener(listener);
        menuItem_color.addActionListener(listener);
        menuItem_size.addActionListener(listener);
        menuItem_style.addActionListener(listener);
        menuItem_close.addActionListener(listener);
        menuItem_help.addActionListener(listener);

        menuItem_new.addActionListener(this);
        menuItem_open.addActionListener(this);
        menuItem_save.addActionListener(this);
        menuItem_saveAs.addActionListener(this);
        menuItem_color.addActionListener(this);
        menuItem_size.addActionListener(this);
        menuItem_style.addActionListener(this);
        menuItem_close.addActionListener(this);
        menuItem_help.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object current = event.getSource();

        if (current == menuItem_open) {
            fileOperation.Open();
        } else if (current == menuItem_save) {
            fileOperation.Save();
        } else if (current == menuItem_saveAs) {
            // System.out.println("Save As");
            fileOperation.SaveAs();
        } else if (current == menuItem_close) {
            if (JOptionPane.showConfirmDialog(this.notepad.frame, "Do you want to close?", "Close..",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                this.notepad.closeNotepad();
            }
        } else if (current == menuItem_new) {
            fileOperation.newFile();
        } else if (current == menuItem_size) {
            System.out.println("Font size");
            int fs;
            String userInput;
            A: do {
                userInput = JOptionPane.showInputDialog(this.notepad.frame, "Enter font size");
                try {
                    fs = Integer.parseInt(userInput);
                    break A;
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(this.notepad.frame, "You can only enter a number");
                }
            } while (true);

            if (fs > 0) {
                Font currentFont = this.notepad.mainArea.textArea.getFont();
                Font newFont = new Font(currentFont.getFamily(), currentFont.getStyle(), fs);

                this.notepad.mainArea.textArea.setFont(newFont);
            }
        } else if (current == menuItem_color) {
            JDialog dialog = new JDialog(this.notepad.frame, "Color Dialog");
            dialog.setLayout(new GridLayout(1, 2));
            JButton textButton, backgroundButton;
            textButton = new JButton("Text");
            backgroundButton = new JButton("Background");

            textButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    Color userInput = JColorChooser.showDialog(notepad.frame, "Select a color", Color.BLACK);
                    if (userInput != null) {
                        notepad.mainArea.textArea.setForeground(userInput);
                    }
                }
            });
            backgroundButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    Color userInput = JColorChooser.showDialog(notepad.frame, "Select a color", Color.BLACK);
                    if (userInput != null) {
                        notepad.mainArea.textArea.setBackground(userInput);
                    }
                }
            });

            dialog.add(textButton, BorderLayout.WEST);
            dialog.add(backgroundButton, BorderLayout.EAST);
            dialog.setLocationRelativeTo(this.notepad.frame);
            dialog.setSize(300, 150);
            dialog.setVisible(true);

        } else if (current == menuItem_help) {
            showHelpDialog();
        } else {
            System.out.println("[MENU BAR] : No match");
        }
    }
}
