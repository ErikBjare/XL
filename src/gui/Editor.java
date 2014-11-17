package gui;

import expr.Expr;
import expr.ExprParser;
import models.CommentSlot;
import models.ExprSlot;
import models.Sheet;
import models.Slot;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {
    private CurrentSlot currentSlot;
    private Sheet sheet;

    public Editor(final Sheet sheet, final CurrentSlot currentSlot) {
        this.currentSlot = currentSlot;
        this.sheet = sheet;
        currentSlot.addObserver(this);

        setBackground(Color.WHITE);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Slot slot;
                String text = getText();
                if (text.length() > 0 && text.charAt(0) != '#') {
                    // If Expression
                    // TODO: Create a exprslot

                    try {
                        Expr expr = new ExprParser().build(text);
                        slot = new ExprSlot(sheet, currentSlot.getAddress(), expr);
                    } catch (IOException err) {
                        // TODO: Do something with error
                        System.err.println("IOERROR");
                        return;
                    }
                    sheet.put(currentSlot.getAddress(), slot);

                    } else if (text.charAt(0) == '#' && text.length() > 1){
                    // If Comment
                    slot = new CommentSlot(sheet, currentSlot.getAddress(), text.substring(1, text.length()));
                    System.out.println(text.substring(1, text.length()));
                    sheet.put(currentSlot.getAddress(), slot);
                    } else {

                    sheet.clear(currentSlot.getAddress());


                }
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        Slot slot = sheet.get(currentSlot.getAddress());
        if (slot != null) {

                setText(slot.getText());

        } else {
            System.out.println("Empty Slot ");
            setText("");
        }
    }
}
