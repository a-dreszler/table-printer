package pl.adreszler.cwiczenie;

import java.util.ArrayList;
import java.util.List;

class Table {

    private final List<Column> columns = new ArrayList<>();
    private final String rowDivider;
    private final int rowNumber;
    private static final int CELL_LEFT_PAD = 1;
    private static final int CELL_RIGHT_PAD = 1;
    private static final StringBuilder BUILDER = new StringBuilder();

    public Table(String[] headerCells, String[][] dataCells) {
        for (int i = 0; i < headerCells.length; i++) {
            List<String> dataColumn = new ArrayList<>();
            for (int j = 0; j < dataCells.length; j++) {
                dataColumn.add(dataCells[j][i]);
            }
            columns.add(new Column(headerCells[i], dataColumn));
        }
        this.rowDivider = getRowDivider();
        this.rowNumber = dataCells.length + 1;
    }

    private String getRowDivider() {
        BUILDER.append("+");
        for (Column column : columns) {
            BUILDER.append("-".repeat(column.getWidth() + 2)).append("+");
        }
        BUILDER.append("\n");
        String rowDivider = BUILDER.toString();
        BUILDER.setLength(0);

        return rowDivider;
    }

    @Override
    public String toString() {
        String leftPad = " ".repeat(CELL_LEFT_PAD);
        String rightPad = " ".repeat(CELL_RIGHT_PAD);
        BUILDER.append(rowDivider);

        for (int i = 0; i < rowNumber; i++) {
            BUILDER.append("|");
            for (Column column : columns) {
                String format = leftPad + "%" + column.getWidth() + "s" + rightPad + "|";
                String cellData = column.getCells().get(i);
                BUILDER.append(String.format(format, cellData));
            }
            BUILDER.append("\n").append(rowDivider);
        }

        String tableStr = BUILDER.toString();
        BUILDER.setLength(0);
        return tableStr;
    }
}