package ru.nsu.minesweeper.repository;

import org.springframework.stereotype.Repository;
import ru.nsu.minesweeper.dto.RecordsRequest;
import ru.nsu.minesweeper.model.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//TODO добавить рекорды

@Repository
public class ApplicationRepository {
    Map<String, Session> sessionMap;
    RecordsTable table;

    public ApplicationRepository() {
        this.sessionMap = new HashMap<>();
    }

    public UUID createSession(int fieldHeight, int fieldWidth, int bombsCount, String player){
        UUID sessionID = UUID.randomUUID();
        sessionMap.put(sessionID.toString(), new Session(sessionID, fieldHeight, fieldWidth, bombsCount, player));
        return sessionID;
    }

    public void readTable(String size) {
        table = new RecordsTable(size);
    }

    public Session getSession(String sessionID) {
        return sessionMap.get(sessionID);
    }

    public RecordsTable getTable() {
        return table;
    }
}
