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

	public void put(String address, Slot slot) {
		slots.put(address, slot);
		setChanged();
		notifyObservers();
	}
	
	public void clear(String address) {
		// TODO: Use when a slot is empty or when to be changed

		if(get(address) != null){
			put(address, new CommentSlot(this, address, ""));

			setChanged();
			notifyObservers();
			slots.remove(address);


		}
	}

	public Set<Entry<String, Slot>> getSlots() {
		return slots.entrySet();
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
