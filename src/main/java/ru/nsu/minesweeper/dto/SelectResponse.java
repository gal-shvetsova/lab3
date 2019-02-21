package ru.nsu.minesweeper.dto;

import ru.nsu.minesweeper.model.CellType;

public class SelectResponse {
    private String state;
    private CellType field[][];

    public String getState() {
        return state;
    }

    public CellType[][] getField() {
        return field;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setField(CellType[][] field) {
        this.field = field;
    }

    public SelectResponse(String state, CellType[][] field) {
        this.state = state;
        this.field = field;
    }
}
