package ru.nsu.minesweeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.nsu.minesweeper.dto.SelectRequest;
import ru.nsu.minesweeper.dto.SelectResponse;
import ru.nsu.minesweeper.dto.StartRequest;
import ru.nsu.minesweeper.dto.StartResponse;
import ru.nsu.minesweeper.model.Session;
import ru.nsu.minesweeper.repository.ApplicationRepository;

import java.util.UUID;


@Service
public class ApplicationService {
    private ApplicationRepository repository;

    @Autowired
    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public StartResponse start(StartRequest startRequest) {
        UUID sessionId = repository.createSession(startRequest.getFieldHeight(), startRequest.getFieldWidth(),
                startRequest.getBombsCount(), startRequest.getPlayer());

        return new StartResponse(sessionId.toString());
    }

    public SelectResponse select(SelectRequest selectRequest) {
        int x = selectRequest.getX(), y = selectRequest.getY();
        Session session = repository.getSession(selectRequest.getSessionID());

        if (!session.isGaming()) {
            session.init(x, y);
            session.startGame();
            return new SelectResponse("game", session.getField());
        }
        if (session.isLose(x, y)) {
            return new SelectResponse("lose", session.getField());
        }


        if (selectRequest.getTypeClick() == 0)
            session.open(x, y);
        if (selectRequest.getTypeClick() == 1)
            session.mark(x,y);

        if (session.isWin()) {
            return new SelectResponse("win", session.getField());
        }

        return new SelectResponse("game", session.getField());
    }
}
