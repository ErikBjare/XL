package models;

import expr.Expr;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cell {
	public Sheet sheet;
	public String address;
	public Expr expr;
	
	public Cell(Sheet sheet, String address) {
		this.sheet = sheet;
		this.address = address;
	}

	public void listenToVars(Sheet sheet) {
		variables();
	}

	public List<String> variables() {
		List<String> vars = new ArrayList<String>();

		Pattern p = Pattern.compile("[A-Z]+[1-9][0-9]*");
		Matcher m = p.matcher(expr.toString());
		while(m.find()) {
			vars.add(m.group());
		}

		return vars;
	}
	
	public void change() {
		// TODO: Do this every time it is changed
		this.sheet.clear(this);
	}
}
