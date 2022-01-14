package com.gmail.kulacholeg.task.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gmail.kulacholeg.task.model.Schedule;
import com.gmail.kulacholeg.task.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchedulesController {

    private final SchedulesService schedulesService;

    @Autowired
    public SchedulesController(SchedulesService schedulesService){
        this.schedulesService = schedulesService;
    }

    @GetMapping("/schedules")
    public List<Schedule> getSchedules(){
        return schedulesService.getAllSchedules();
    }

    @GetMapping("/schedules/{id}")
    public Schedule getOneSchedule(@PathVariable Long id){
        return schedulesService.getOneSchedule(id);
    }

    @PostMapping("/schedules")
    public Schedule addSchedule(@RequestBody ObjectNode node){
        return schedulesService.addSchedule(node);
    }

    @PutMapping("/schedules/{id}")
    public Schedule updateScheduleById(@PathVariable Long id,
                                       @RequestBody ObjectNode node){
        return schedulesService.updateScheduleById(node, id);
    }

    @DeleteMapping("/schedules/{id}")
    public HttpStatus deleteSchedule(@PathVariable Long id){
        return schedulesService.deleteScheduleById(id);
    }
}
