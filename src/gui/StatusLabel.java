package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StatusLabel extends ColoredLabel {
    Timer timer;

    public StatusLabel() {
        super("", Color.WHITE);
    }


    @Override
    public void setText(String text) {
        super.setText(text);
        if(timer == null) {
            timer = new Timer(0, new ResetAction(this));
            timer.setRepeats(false);
            timer.setInitialDelay(5000);
        } else {
            timer.stop();
        }
        timer.start();
    }

class ResetAction implements ActionListener {
    public StatusLabel label;

    public ResetAction(StatusLabel label) {
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText("");
    }
}
}