package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {
    public Editor() {
        setBackground(Color.WHITE);
    }

    @Override
    public void update(Observable observable, Object o) {
        // TODO: Set expression to slot and read input of current slot
    }
}