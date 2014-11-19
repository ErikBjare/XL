package gui.menu;

import gui.StatusLabel;
import gui.XL;
import io.XLPrintStream;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

class SaveMenuItem extends OpenMenuItem {
    public SaveMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Save");
    }

    protected void action(String path) throws FileNotFoundException {
        XLPrintStream ps = new XLPrintStream(path);
        ps.save(xl.getSheet().getSlots().entrySet());
        statusLabel.setText("Successfully saved!");
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}