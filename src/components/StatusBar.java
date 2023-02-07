package components;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StatusBar extends JLabel {
    public StatusBar() {
        super("Characters: ");
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public StatusBar(String content) {
        super("Characters: " + content);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public String setContent(String content) {
        this.setText("Characters: " + content);
        return this.getText();
    }

}
