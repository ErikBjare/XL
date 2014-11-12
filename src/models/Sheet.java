package models;

import expr.Environment;
import expr.ExprParser;
import util.XLException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;
import java.util.Set;

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

class XLPrintStream extends PrintStream {
	public XLPrintStream(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public void save(Set<Entry<String, Slot>> set) {
		for (Entry<String, Slot> entry : set) {
			print(entry.getKey());
			print('=');
			println(entry.getValue().toString());
		}
		flush();
		close();
	}
}

class XLBufferedReader extends BufferedReader {
	public XLBufferedReader(String name) throws FileNotFoundException {
		super(new FileReader(name));
	}

	public void load(Map<String, Slot> map) {
		try {
			while (ready()) {
				String string = readLine();
				int i = string.indexOf('=');
				// TODO
			}
		} catch (Exception e) {
			throw new XLException(e.getMessage());
		}
	}
}
