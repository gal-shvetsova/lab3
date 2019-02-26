package ru.nsu.minesweeper.dto;

import ru.nsu.minesweeper.model.CellType;

public class SelectResponse {
    private String state;
    private CellType field[][];
    private int bombs;

    public String getState() {
        return state;
    }

    public CellType[][] getField() {
        return field;
    }

    public int getBombs() {
        return bombs;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setField(CellType[][] field) {
        this.field = field;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public SelectResponse(String state, CellType[][] field, int bombs) {
        this.state = state;
        this.field = field;
        this.bombs = bombs;
    }
}
