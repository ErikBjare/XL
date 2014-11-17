package gui;

import expr.Expr;
import expr.ExprParser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {
    public Editor() {
        setBackground(Color.WHITE);
        ActionListener enterAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getText().charAt(0) == '#') {
                    // If Comment
                    // TODO: Create a commentslot
                    System.out.println("Commentslot to-be");
                } else {
                    // If Expression
                    // TODO: Create a exprslot
                    System.out.println("KLINGONS EVERYWHERE, they are forcing you to evaluate the expression: " + getText());
                    try {
                        Expr expr = new ExprParser().build(getText());
                        System.out.println("You succeeded and they gave you 10 bars of gold-pressed latinum: " + expr);
                    } catch (IOException err) {
                        System.out.println("Klingons killed you for failing to evaluate the expression.");
                    }
                }
            }
        };
        addActionListener(enterAction);
    }

    @Override
    public void update(Observable observable, Object o) {
        // TODO: Set expression to slot and read input of current slot

    }
}
