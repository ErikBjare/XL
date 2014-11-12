package models;

import util.XLException;

public class CommentSlot extends Slot {
    public String comment;

    public CommentSlot(Sheet sheet, String address, String comment) {
        super(sheet, address);
        this.comment = comment;
    }

    @Override
    public double value(Sheet sheet) {
        // TODO: Correct exception? Correct msg?
        throw new XLException("TODO");
    }

    @Override
    public String toString() {
        return comment;
    }
}
