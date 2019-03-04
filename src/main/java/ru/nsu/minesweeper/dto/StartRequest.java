package ru.nsu.minesweeper.dto;

public class StartRequest {

    private int fieldHeight;
    private int fieldWidth;
    private int bombsCount;
    private String size;

    public int getBombsCount() {
        return bombsCount;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public String getSize() {
        return size;
    }
}
