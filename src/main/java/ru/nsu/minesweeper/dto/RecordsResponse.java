package ru.nsu.minesweeper.dto;

import java.util.ArrayList;

public class RecordsResponse {
    private ArrayList<String> players;
    private ArrayList<Integer> records;

    public RecordsResponse(ArrayList<String> players, ArrayList<Integer> records) {
        this.players = players;
        this.records = records;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public ArrayList<Integer> getRecords() {
        return records;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public void setRecords(ArrayList<Integer> records) {
        this.records = records;
    }
}
