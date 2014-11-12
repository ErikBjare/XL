package models;

import expr.Environment;
import expr.ExprParser;
import util.XLException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;

public class Sheet extends Observable implements Environment {
	public HashMap<String, Slot> cells;
	
	public Slot get(String address) {
		if (!cells.containsKey(address)) {
			ExprParser ep = new ExprParser();
			try {
				Slot slot = new ExprSlot(this, address, ep.build("0"));
				cells.put(address, slot);
				return slot;
			} catch (IOException e) {
				throw new XLException("This should never happen");
			}
		} else {
			return cells.get(address);	
		}
	}

	public void put(String address, Slot slot) {
		cells.put(address, slot);
	}
	
	public void clear(Slot slot) {
		// TODO: Use when a slot is empty or when to be changed
		cells.remove(slot.address);
	}

	@Override
	public double value(String name) {
		return get(name).value(this);
	}
}
