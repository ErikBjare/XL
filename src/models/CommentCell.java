package models;

import expr.Environment;
import util.XLException;

public class CommentCell extends Cell {
    public String comment;

    public CommentCell(Sheet sheet, String address, String comment) {
        super(sheet, address);
        this.comment = comment;
    }

    @Override
    public double value(Environment env) {
        // TODO: Correct exception? Correct msg?
        throw new XLException("TODO");
    }

    @Override
    public String toString() {
        return comment;
    }
}
