package io;

import models.Sheet;
import models.Slot;
import models.SlotFactory;
import util.XLException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class XLBufferedReader extends BufferedReader {
    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Sheet sheet) {
        Map<String, Slot> map = sheet.getSlots();
        try {
            while (ready()) {
                String string = readLine();
                int i = string.indexOf('=');
                String addr = string.substring(0, i);
                Slot slot = SlotFactory.build(addr, sheet, string.substring(i + 1));
                map.put(addr, slot);
            }
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}
