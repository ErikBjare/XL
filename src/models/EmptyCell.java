package models;

import expr.Environment;

public class EmptyCell extends Cell {
    public EmptyCell(Sheet sheet, String address) {
        super(sheet, address);
    }

    @Override
    public double value(Environment env) {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
