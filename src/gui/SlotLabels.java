package gui;

import models.CommentSlot;
import models.Sheet;
import models.Slot;

import java.awt.Color;
import java.util.*;
import javax.swing.SwingConstants;
import javax.xml.stream.events.Comment;

public class SlotLabels extends GridPanel implements Observer {
    private List<SlotLabel> labelList;
    int cols;
    private Sheet sheet;
    private CurrentSlot currentSlot;
    private int lastIdx = 0;

    public SlotLabels(int rows, int cols, CurrentSlot currentSlot, Sheet sheet) {
        super(rows + 1, cols);

        // TODO: Remove this by solving ref in update()?
        this.cols = cols;
        this.sheet = sheet;

        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY,
                    SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                SlotLabel label = new SlotLabel("" + ch + row, this);
                add(label);
                labelList.add(label);
            }
        }

        // TODO: Set current slot upon start
        this.currentSlot = currentSlot;
        currentSlot.addObserver(this);
        sheet.addObserver(this);
        setBG("A1");
    }

    private int addrToIdx(String addr) {
        return (Integer.parseInt(addr.substring(1)) - 1) * cols + (addr.charAt(0) - 'A');
    }

    public void setBG(String addr) {
        // TODO: Only supports A1-Z9
        labelList.get(lastIdx).setBackground(Color.WHITE);
        int i = addrToIdx(addr);
//        System.out.println("Setting i=" + i + " to yellow bg");
        labelList.get(i).setBackground(Color.YELLOW);
        lastIdx = i;
    }

    public CurrentSlot getCurrent() {
        return currentSlot;
    }

    @Override
    public void update(Observable observable, Object o) {
        // TODO: Observe current slot and set background upon update
        if (observable == currentSlot) {
            setBG(currentSlot.getAddress());
        } else if (observable == sheet) {
            for(SlotLabel sl : labelList) {
                sl.setText("");
            }
            for (Map.Entry<String, Slot> e : sheet.getSlots().entrySet()) {
                Slot value = e.getValue();
                System.out.println(e.getKey()+ " gives value " +value);
                if(value!= null){
                SlotLabel s = labelList.get(addrToIdx(value.getAddress()));
                //TODO: set text as toString of the slot or value?
                if (e.getValue() instanceof CommentSlot) {
                    s.setText(e.getValue().toString());
                } else {
                    try{
                        s.setText(Double.toString(e.getValue().value(sheet)));
                    } catch (NullPointerException x) {
                        System.out.println("Referring to a slot that contains nothing");
                        s.setText(e.getValue().toString());
                    }
                };
            }
        }}
    }
}
