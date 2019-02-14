package ru.nsu.minesweeper.dto;

import ru.nsu.minesweeper.model.cellType;

public class SelectResponse {
    private String state;
    private cellType field[][];

    public SelectResponse(String state, cellType[][] field) {
        this.state = state;
        this.field = field;
    }
}
