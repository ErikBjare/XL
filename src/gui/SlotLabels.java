package gui;

import models.CurrentSlot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel implements Observer {
    private List<SlotLabel> labelList;
    int rows;

    public SlotLabels(int rows, int cols) {
        super(rows + 1, cols);

        // TODO: Remove this by solving ref in update()?
        this.rows = rows;

        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY,
                    SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                SlotLabel label = new SlotLabel("" + ch + row);
                add(label);
                labelList.add(label);
            }
        }

        // TODO: Set current slot upon start
        SlotLabel firstLabel = labelList.get(0);
        firstLabel.setBackground(Color.YELLOW);
    }

    @Override
    public void update(Observable observable, Object o) {
        // TODO: Observe current slot and set background upon update
        String s = ((CurrentSlot)o).getAddress();
        int i = Integer.parseInt(s.substring(1))*rows + (s.charAt(0) - 'A');
        System.out.println("Setting i=" + i + " to yellow bg");
        labelList.get(i).setBackground(Color.YELLOW);
    }
}
