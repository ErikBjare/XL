package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import gui.menu.XLMenuBar;
import models.Sheet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import javax.swing.*;

public class XL extends JFrame implements Printable {
    private static final int ROWS = 20, COLUMNS = 20;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;
    private Sheet sheet;
    private CurrentSlot currentSlot;

    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();

        currentSlot = new CurrentSlot("A1");
        sheet = new Sheet();
        JPanel statusPanel = new StatusPanel(statusLabel, currentSlot);
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, currentSlot, sheet);
        Editor editor = new Editor(sheet, currentSlot, statusLabel);
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public Sheet getSheet(){
        return sheet;
    }
    public CurrentSlot getCurrentSlot() {
        return currentSlot;
    }

    public int print(Graphics g, PageFormat pageFormat, int page) {
        if (page > 0)
            return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printAll(g2d);
        return PAGE_EXISTS;
    }

    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
}