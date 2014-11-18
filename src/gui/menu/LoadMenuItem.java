package gui.menu;

import gui.StatusLabel;
import gui.XL;
import io.XLBufferedReader;
import models.Sheet;
import util.XLException;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

class LoadMenuItem extends OpenMenuItem {
 
    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
      super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
        Sheet sheet = xl.getSheet();
        sheet.clearAll();

        try {

            XLBufferedReader xlBufferedReader = new XLBufferedReader(path);
            xlBufferedReader.load(sheet);
            sheet.externallyChanged();

        } catch (XLException e) {
            //TODO add to status panel
            statusLabel.setText("Error loading file: " + e.getMessage());
            sheet.clearAll();
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}