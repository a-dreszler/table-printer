package pl.adreszler.cwiczenie;

import java.util.*;

class Column {

    private final List<String> cells = new ArrayList<>();
    private final int width;

    public Column(String header, List<String> dataCells) {
        cells.add(header);
        cells.addAll(dataCells);
        width = cells.stream()
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(-1);
    }

    public List<String> getCells() {
        return cells;
    }

    public int getWidth() {
        return width;
    }
}
