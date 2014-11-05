package models;

import java.util.HashMap;
import java.util.Observable;

public class Sheet extends Observable {
	public HashMap<String, Cell> cells;
	
	public Cell get(String address) {
		if (!cells.containsKey(address)) {
			Cell cell = new Cell(this, address);
			cells.put(address, cell);
			return cell;
		} else {
			return cells.get(address);	
		}
	}
	
	public void clear(Cell cell) {
		cells.remove(cell.address);
	}
}
