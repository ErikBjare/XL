package gui;

import expr.Expr;
import expr.ExprParser;
import models.CommentSlot;
import models.ExprSlot;
import models.Sheet;
import models.Slot;
import util.XLException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class Editor extends JTextField implements Observer {
    private CurrentSlot currentSlot;
    private Sheet sheet;
    private StatusLabel statusLabel;

    public Editor(final Sheet sheet, final CurrentSlot currentSlot, final StatusLabel statusLabel) {
        this.currentSlot = currentSlot;
        this.sheet = sheet;
        this.statusLabel = statusLabel;
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

                    Slot oldslot = sheet.get(currentSlot.getAddress());

                    try {
                        Expr expr = new ExprParser().build(text);
                        slot = new ExprSlot(sheet, currentSlot.getAddress(), expr);
                        sheet.put(currentSlot.getAddress(), slot);
                    } catch (IOException err) {
                        // TODO: Do something with error
                        statusLabel.setText("Invalid Expression.");
                        return;
                    } catch (XLException err) {
                        try{
                            sheet.put(currentSlot.getAddress(), oldslot);
                        }catch (NullPointerException f){
                            statusLabel.setText("An empty slot may not be referenced to.");
                        }catch (XLException f){
                            statusLabel.setText(f.getMessage());
                        } statusLabel.setText(err.getMessage());


                    }catch (NullPointerException f){
                        statusLabel.setText("An empty slot may not be referenced to.");
                    }
                } else if (text.length() > 1 && text.charAt(0) == '#') {
                    // If Comment
                    slot = new CommentSlot(sheet, currentSlot.getAddress(), text.substring(1, text.length()));
//

                    sheet.put(currentSlot.getAddress(), slot);
                } else {
                    try{
                        sheet.clear(currentSlot.getAddress());
                    }catch (XLException f){
                        statusLabel.setText(f.getMessage());

                    }
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
//            System.out.println("Empty Slot ");
            setText("");
        }
    }
}
