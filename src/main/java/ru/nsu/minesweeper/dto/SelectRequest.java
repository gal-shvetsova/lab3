package ru.nsu.minesweeper.dto;

public class SelectRequest {
    private int x;
    private int y;
    private String sessionID;
    private int typeClick;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSessionID() {
        return sessionID;
    }

    public int getTypeClick() {
        return typeClick;
    }
}
