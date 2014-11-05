package models;

import expr.Expr;

public class Cell {
	public Sheet sheet;
	public String address;
	public Expr expr;
	
	public Cell(Sheet sheet, String address) {
		this.sheet = sheet;
		this.address = address;
	}
	
	public void change() {
		// TODO: Do this every time it is changed
		this.sheet.clear(this);
	}
}
