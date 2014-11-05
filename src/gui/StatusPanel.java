package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel {
    protected StatusPanel(StatusLabel statusLabel) {
        add(WEST);
        add(CENTER, statusLabel);
    }
}