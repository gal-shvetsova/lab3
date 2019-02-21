package ru.nsu.minesweeper.dto;


public class StartResponse {
    private String sessionID;

    public StartResponse(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

}
