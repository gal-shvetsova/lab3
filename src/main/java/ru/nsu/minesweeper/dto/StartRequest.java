package ru.nsu.minesweeper.dto;

public class StartRequest {

    private int fieldHeight;
    private int fieldWidth;
    private int bombsCount;
    private String player;

    public int getBombsCount() {
        return bombsCount;
    }

    public String getPlayer() {
        return player;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }
}
