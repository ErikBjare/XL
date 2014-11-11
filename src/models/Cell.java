package models;

import expr.Environment;

public abstract class Cell {
    public Sheet sheet;
    public String address;

    public Cell(Sheet sheet, String address) {
        this.sheet = sheet;
        this.address = address;
    }

    public abstract double value(Environment env);
    public abstract String toString();

    public void change() {
        // TODO: Do this every time it is changed
        this.sheet.clear(this);
    }
}
