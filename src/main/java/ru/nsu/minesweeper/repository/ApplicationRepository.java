package ru.nsu.minesweeper.repository;

import org.springframework.stereotype.Repository;
import ru.nsu.minesweeper.model.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ApplicationRepository {
    Map<UUID, Session> sessionMap;

    public ApplicationRepository() {
        this.sessionMap = new HashMap<>();
    }




    public UUID createSession(int fieldHeight, int fieldWidth, int bombsCount, String player){
        UUID sessionId = UUID.randomUUID();
        sessionMap.put(sessionId, new Session(sessionId, fieldHeight, fieldWidth, bombsCount, player));
        return sessionId;
    }
}
