package gui;


class RowLabels {
    RowLabels(int rows) {
        super(rows + 1, 1);
        add();
        for (int i = 1; i <= rows; i++) {
            add();
        }
    }
}