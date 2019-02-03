package ru.nsu.minesweeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.nsu.minesweeper.dto.StartRequest;
import ru.nsu.minesweeper.dto.StartResponse;
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
        UUID sessionId = repository.createSession(startRequest.getSizeCode());
        return new StartResponse(sessionId.toString());
    }
}
