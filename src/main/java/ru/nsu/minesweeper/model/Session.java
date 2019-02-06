package ru.nsu.minesweeper.model;

import java.util.UUID;

public class Session {
    private UUID sessionID;
    private String sizeCode;  //TODO: убрать sizeCode добавить значения ширины высоты и мин

    private int fieldHeight;
    private int fieldWidth;
    private int bombsCount;
    private String player;

    public Session(UUID sessionID, int fieldHeight, int fieldWidth, int bombsCount, String player) {
        this.sessionID = sessionID;
        this.sizeCode = sizeCode;
    }

    public UUID getSessionID() {
        return sessionID;
    }

    public String getSizeCode() {
        return sizeCode;
    }

}
