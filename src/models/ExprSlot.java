package models;

import expr.Expr;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExprSlot extends Slot {
	public Expr expr;
	
	public ExprSlot(Sheet sheet, String address, Expr expr) {
		super(sheet, address);
		this.expr = expr;
	}

	public double value(Sheet sheet) {
		sheet.put(address, new BombSlot(sheet, address));
		double val = expr.value(sheet);
		sheet.put(address, this);
		return val;
	}

	@Override
	public String toString() {
		return expr.toString();
	}

	public void listenToVars(Sheet sheet) {
		for(String var : variables()) {
			sheet.get(var).addObserver(this);
		};
	}

	@Override
	public void change() {
		for (String var : variables()) {
			sheet.get(var).deleteObserver(this);
		}
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
}
