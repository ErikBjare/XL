package models;

public class CommentSlot extends Slot {
    public String comment;

    public CommentSlot(Sheet sheet, String address, String comment) {
        super(sheet, address);
        this.comment = comment;
    }

    @Override
    public double value(Sheet sheet) {
        return 0;
    }

    @Override
    public String toString() {
        return comment;
    }
    public String getText(){
        return '#'+comment;
    }
}
