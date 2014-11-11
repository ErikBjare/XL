package models;

import expr.Environment;
import expr.Expr;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExprCell extends Cell {
	public Expr expr;
	
	public ExprCell(Sheet sheet, String address) {
		super(sheet, address);
	}

	public double value(Environment env) {
		return expr.value(env);
	}

	@Override
	public String toString() {
		return expr.toString();
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
}
