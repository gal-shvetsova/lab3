package ru.nsu.minesweeper.dto;

import ru.nsu.minesweeper.model.cellType;

public class SelectResponse {
    private String state;
    private cellType field[][];

    public String getState() {
        return state;
    }

    public cellType[][] getField() {
        return field;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setField(cellType[][] field) {
        this.field = field;
    }

    public SelectResponse(String state, cellType[][] field) {
        this.state = state;
        this.field = field;
    }
}
