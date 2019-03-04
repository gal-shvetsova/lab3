package ru.nsu.minesweeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.minesweeper.dto.*;
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

    @PostMapping(value = "/records")
    public RecordsResponse getRecords(@RequestBody RecordsRequest data) {
        return  service.getRecords(data);
    }

    @PostMapping(value = "/newrecord")
    public NewRecordResponse addRecord(@RequestBody NewRecordRequest data) {
        return service.addRecord(data);
    }
}
