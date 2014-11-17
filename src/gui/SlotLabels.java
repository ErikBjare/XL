package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel implements Observer {
    private List<SlotLabel> labelList;
    int cols;
    private CurrentSlot currentSlot;
    private int lastIdx = 0;

    public SlotLabels(int rows, int cols, CurrentSlot currentSlot) {
        super(rows + 1, cols);

        // TODO: Remove this by solving ref in update()?
        this.cols = cols;

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
        setBG("A1");
    }

    public void setBG(String addr) {
        // TODO: Only supports A1-Z9
        labelList.get(lastIdx).setBackground(Color.WHITE);
        int i = (Integer.parseInt(addr.substring(1))-1)*cols + (addr.charAt(0) - 'A');
        System.out.println("Setting i=" + i + " to yellow bg");
        labelList.get(i).setBackground(Color.YELLOW);
        lastIdx = i;
    }

    public CurrentSlot getCurrent() {
        return currentSlot;
    }

    @Override
    public void update(Observable observable, Object o) {
        // TODO: Observe current slot and set background upon update
        setBG(((CurrentSlot) observable).getAddress());

    }
}
