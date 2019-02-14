package ru.nsu.minesweeper.dto;


import java.util.UUID;

public class StartResponse {
    private String sessionID;

    public StartResponse(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

}
