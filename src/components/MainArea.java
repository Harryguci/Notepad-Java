package components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;

public class MainArea extends JPanel implements KeyListener {
    final public static int g_Width = 300;
    final public static int g_Height = 300;
    JTextArea textArea;
    Notepad notepad;

    public MainArea(Notepad notepad) {
        super(new BorderLayout());
        this.notepad = notepad;
        setForeground(Color.BLUE);
        textArea = new JTextArea(30, 50);
        add(textArea, BorderLayout.CENTER);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        setBackground(null);
    }

    public MainArea(MainArea element) {
        this.textArea = element.textArea;
    }

    public String getText() {
        return this.textArea.getText().toString();
    }

    public JTextArea getTextArea() {
        return this.textArea;
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void appendContent(String text) {
        String currentContent = this.textArea.getText();
        currentContent += text;
        setText(currentContent);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MainArea mainArea = new MainArea(new Notepad());
        mainArea.addKeyListener(new MainArea(mainArea));

        frame.add(new JScrollPane(mainArea), BorderLayout.CENTER);
        frame.add(new JLabel(" "), BorderLayout.WEST);
        frame.add(new JLabel(" "), BorderLayout.EAST);
        frame.add(new JLabel("Status", new ImageIcon(""), SwingConstants.CENTER), BorderLayout.SOUTH);
        frame.setVisible(true);

        frame.pack();

        frame.setLocation(100, 50);
        frame.setVisible(true);
    }

    @Override
    public void addKeyListener(KeyListener listener) {
        textArea.addKeyListener(listener);
        textArea.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        notepad.statusBar.setText("Characters: " + this.textArea.getText().length());
        notepad.fileOperation.setChanged(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
