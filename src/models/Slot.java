package models;

import java.util.Observable;
import java.util.Observer;

public abstract class Slot extends Observable implements Observer {
    protected Sheet sheet;
    protected String address;

    public Slot(Sheet sheet, String address) {
        this.sheet = sheet;
        this.address = address;
    }

    public abstract double value(Sheet sheet);

    public abstract String toString();
    public abstract String getText();

    public String getAddress() {

        return address;
    }


    public void change() {
        // TODO: Do this every time it is changed
        //this.sheet.clear(this);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
