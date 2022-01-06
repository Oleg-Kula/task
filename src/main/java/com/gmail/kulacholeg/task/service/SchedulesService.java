package com.gmail.kulacholeg.task.service;

import com.gmail.kulacholeg.task.model.Schedule;
import com.gmail.kulacholeg.task.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulesService {

    private final SchedulesRepository repository;

    @Autowired
    public SchedulesService(SchedulesRepository repository){
        this.repository = repository;
    }

    public List<Schedule> getAllSchedules(){
        return repository.findAll();
    }
}
