package ru.nsu.minesweeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.minesweeper.dto.SelectRequest;
import ru.nsu.minesweeper.dto.SelectResponse;
import ru.nsu.minesweeper.dto.StartRequest;
import ru.nsu.minesweeper.dto.StartResponse;
import ru.nsu.minesweeper.service.ApplicationService;

@RestController
public class ApplicationController {

    private ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping(value = "/start")
    public StartResponse start(@RequestBody StartRequest data) {
        return service.start(data);
    }

    @PostMapping(value = "/select")
    public SelectResponse select(@RequestBody SelectRequest data) {
        return service.select(data);
    }
}
