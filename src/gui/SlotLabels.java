package gui;

import models.CommentSlot;
import models.Sheet;
import models.Slot;

import java.awt.Color;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel implements Observer {
    private List<SlotLabel> labelList;
    int cols;
    private Sheet sheet;
    private CurrentSlot currentSlot;
    private int lastIdx = 0;

    public SlotLabels(int rows, int cols, CurrentSlot currentSlot, Sheet sheet) {
        super(rows + 1, cols);

        this.cols = cols;
        this.sheet = sheet;

        List<String> colStrings = new ArrayList<String>();

        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (int i=0; i<cols; i++) {
            char c = (char)('A' + i % ('Z'-'A'+1));
            String s = String.valueOf(c);
            int preidx = i/('Z'-'A'+1);
            if(preidx > 0) {
                s = (char)('A'+preidx-1) + s;
            }
            colStrings.add(s);
            add(new ColoredLabel(s, Color.LIGHT_GRAY,
                    SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (String col : colStrings) {
                SlotLabel label = new SlotLabel("" + col + row, this);
                add(label);
                labelList.add(label);
            }
        }

        this.currentSlot = currentSlot;
        currentSlot.addObserver(this);
        sheet.addObserver(this);
        setBG("A1");
    }

    private int addrToIdx(String addr) {
        Pattern colpattern = Pattern.compile("[A-Z]+");
        Matcher colmatcher = colpattern.matcher(addr);
        colmatcher.find();
        int end = colmatcher.end();

        int row = Integer.parseInt(addr.substring(end)) - 1;
        String colstr = addr.substring(0, end);
        int col = 0;
        for(int i=0; i<colstr.length(); i++) {
            col += (colstr.charAt(i)-'A') + i*('Z'-'A'+1);
        }

        return row * cols + col;
    }

    public void setBG(String addr) {

        labelList.get(lastIdx).setBackground(Color.WHITE);
        int i = addrToIdx(addr);

        labelList.get(i).setBackground(Color.YELLOW);
        lastIdx = i;
    }

    public CurrentSlot getCurrent() {
        return currentSlot;
    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable == currentSlot) {
            setBG(currentSlot.getAddress());
        } else if (observable == sheet) {
            for(SlotLabel sl : labelList) {
                sl.setText("");
            }
            for (Map.Entry<String, Slot> e : sheet.getSlots().entrySet()) {
                Slot value = e.getValue();

                if(value!= null){
                SlotLabel s = labelList.get(addrToIdx(value.getAddress()));
                             if (e.getValue() instanceof CommentSlot) {
                    s.setText(e.getValue().toString());
                } else {
                    try{
                        String textValue = Double.toString(e.getValue().value(sheet));
                        s.setText(textValue);
                    } catch (NullPointerException x) {
                        //NOTE: Should not happen (?)
                    }
                }
            }
        }}
    }
}
