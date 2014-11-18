package models;

import expr.Expr;
import expr.ExprParser;
import util.XLException;

import java.io.IOException;

public class SlotFactory {
    public static Slot build(String address, Sheet sheet, String str) {
        if(str.charAt(0) == '#') {
            return new CommentSlot(sheet, address, str);
        } else {
            try {
                Expr expr = new ExprParser().build(str);
                return new ExprSlot(sheet, address, expr);
            } catch(IOException err) {
                throw new XLException("Failed to create slot");
            }
        }
    }
}
