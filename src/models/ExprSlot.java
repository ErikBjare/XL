package models;

import expr.Expr;
import util.XLException;

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
		sheet.putWithoutUpdate(address, new BombSlot(sheet, address));
		double val;
		try {
			val = expr.value(sheet);
		} catch(XLException err) {
			sheet.putWithoutUpdate(address, this);
			throw err;
		}
		sheet.putWithoutUpdate(address, this);
		return val;
	}

	@Override
	public String toString() {
		return expr.toString();
	}

	@Override
	public String getText()  {
		return expr.toString();
	}

	public void startObserving() {
		for(String var : variables()) {
			Slot slot = sheet.get(var);
			if(slot == null) {
				throw XLException.NULLSLOT_ERROR;
			} else {
				slot.addObserver(this);
			}
		};
	}
	
	public void stopObserving() {
		for(String var : variables()) {
			sheet.get(var).deleteObserver(this);
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
