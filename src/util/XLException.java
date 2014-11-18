package util;

public class XLException extends RuntimeException {
    public static final XLException NULLSLOT_ERROR = new XLException("Referring to an empty slot is not allowed");
    public static final XLException RECURSION_ERROR = new XLException("Recursive expressions are not allowed");
    public static final XLException REMOVEREFERENCED_ERROR = new XLException("Removing referenced slot not allowed");

    public XLException(String message) {
        super(message);
    }
}