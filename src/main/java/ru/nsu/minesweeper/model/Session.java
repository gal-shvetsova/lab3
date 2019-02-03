package ru.nsu.minesweeper.model;

import java.util.UUID;

public class Session {
    private UUID sessionID;
    private String sizeCode;

    public Session(UUID sessionID, String sizeCode) {
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
