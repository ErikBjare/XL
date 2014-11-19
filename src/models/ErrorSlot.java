package models;

import util.XLException;

public class ErrorSlot extends Slot {
    public ErrorSlot(Sheet sheet, String address) {
        super(sheet, address);
    }

    @Override
    public double value(Sheet sheet) {
        throw XLException.RECURSION_ERROR;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String getText() {
        return "";
    }
}
