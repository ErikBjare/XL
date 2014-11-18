package gui;

import java.util.Observable;

public class CurrentSlot extends Observable {
    private String address;

    public CurrentSlot(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
        setChanged();
        notifyObservers();
    }
}
