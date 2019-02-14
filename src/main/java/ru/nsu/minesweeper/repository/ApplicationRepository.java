package ru.nsu.minesweeper.repository;

import org.springframework.stereotype.Repository;
import ru.nsu.minesweeper.model.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//TODO добавить рекорды

@Repository
public class ApplicationRepository {
    Map<String, Session> sessionMap;

    public ApplicationRepository() {
        this.sessionMap = new HashMap<>();
    }

    public String createSession(int fieldHeight, int fieldWidth, int bombsCount, String player){
        UUID sessionID = UUID.randomUUID();
        sessionMap.put(sessionID.toString(), new Session(sessionID, fieldHeight, fieldWidth, bombsCount, player));
        return sessionID.toString();
    }

    public Session getSession(String sessionID) {
        return sessionMap.get(sessionID);
    }
}
