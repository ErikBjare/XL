package models;

import util.XLException;

public class BombSlot extends Slot {
    public BombSlot(Sheet sheet, String address) {
        super(sheet, address);
    }

    @Override
    public double value(Sheet sheet) {
        throw new XLException("BOOM");
    }

    @Override
    public String toString() {
        return "";
    }
}
