package com.gmail.kulacholeg.task.controller;

import com.gmail.kulacholeg.task.model.Schedule;
import com.gmail.kulacholeg.task.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
