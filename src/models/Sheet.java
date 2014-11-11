package models;

import expr.Environment;

import java.util.HashMap;
import java.util.Observable;

public class Sheet extends Observable implements Environment {
	public HashMap<String, ExprCell> cells;
	
	public ExprCell get(String address) {
		if (!cells.containsKey(address)) {
			ExprCell cell = new ExprCell(this, address);
			cells.put(address, cell);
			return cell;
		} else {
			return cells.get(address);	
		}
	}
	
	public void clear(Cell cell) {
		cells.remove(cell.address);
	}

	@Override
	public double value(String name) {
		return get(name).value(this);
	}
}
