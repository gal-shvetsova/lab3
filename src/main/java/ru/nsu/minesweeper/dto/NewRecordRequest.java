package ru.nsu.minesweeper.dto;

public class NewRecordRequest {
    private String player;
    private int time;
    private String size;

    public String getPlayer() {
        return player;
    }

    public int getTime() {
        return time;
    }

    public String getSize() {
        return size;
    }
}
