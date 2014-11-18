package models;

import expr.Environment;
import util.XLException;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Sheet extends Observable implements Environment {
    private Map<String, Slot> slots;

    public Sheet() {
        slots = new HashMap<String, Slot>();
    }

    public Slot get(String address) {
        if (!slots.containsKey(address)) {
            return null;
        } else {
            return slots.get(address);
        }
    }

    public void externallyChanged() {
        for(Slot slot : slots.values()) {
            if (slot instanceof ExprSlot) {
                ((ExprSlot) slot).startObserving();
            }
        }
        setChanged();
        notifyObservers();
    }

    public void clearAll(){
        slots = new HashMap<String, Slot>();
        setChanged();
        notifyObservers();
    }

    public void put(String address, Slot slot) {
        putWithoutUpdate(address, slot);
        if(slot instanceof ExprSlot) {
            ((ExprSlot) slot).startObserving();
        }
        setChanged();
        notifyObservers();
    }

    public void putWithoutUpdate(String address, Slot slot) {
        slots.put(address, slot);
    }

    public void clear(String address) {
        Slot slot = get(address);
        if (slot != null && slot.countObservers() == 0) {
            //Remove slot if no observers
            put(address, new CommentSlot(this, address, ""));
            setChanged();
            notifyObservers();
            if(slot instanceof ExprSlot) {
                ((ExprSlot) slot).stopObserving();
            }
            slots.remove(address);
            setChanged();
            notifyObservers();
        } else {

            throw XLException.REMOVEREFERENCED_ERROR;
        }
    }

    public Map<String, Slot> getSlots() {
        return slots;
    }

    @Override
    public double value(String name) {
        return get(name).value(this);
    }
}

