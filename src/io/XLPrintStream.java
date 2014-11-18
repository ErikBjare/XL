package io;

import models.Slot;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

public class XLPrintStream extends PrintStream {
    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public void save(Set<Map.Entry<String, Slot>> set) {
        for (Map.Entry<String, Slot> entry : set) {
            print(entry.getKey());
            print('=');
            println(entry.getValue().toString());
        }
        flush();
        close();
    }
}
