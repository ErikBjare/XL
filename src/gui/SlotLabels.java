package gui;

import java.awt.Color;

public class SlotLabels {
    public SlotLabels(int rows, int cols) {
        super(rows + 1, cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add();
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                add(label);
                labelList.add(label);
            }
        }
        SlotLabel firstLabel = labelList.get(0);
        firstLabel.setBackground(Color.YELLOW);
    }
}
