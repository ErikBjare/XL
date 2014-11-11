package models;

import expr.Environment;

import java.util.HashMap;
import java.util.Observable;

public class Sheet extends Observable implements Environment {
	public HashMap<String, Cell> cells;
	
	public Cell get(String address) {
		if (!cells.containsKey(address)) {
			// TODO: What should be done in this case?
			// Zero expr would be preferred but not possible due to Num not being exported.
			// EmptyCell is a temporary (or is it?) fix.
			Cell cell = new EmptyCell(this, address);
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
