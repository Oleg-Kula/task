package com.gmail.kulacholeg.task.service;

import com.gmail.kulacholeg.task.model.Day;
import com.gmail.kulacholeg.task.repository.DaysRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DaysService {

    private final DaysRepository daysRepository;

    public DaysService(DaysRepository daysRepository){
        this.daysRepository = daysRepository;
    }

    public List<Day> getDays(){
        return daysRepository.findAll();
    }

    public Day getDayById(Long id){
        return daysRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Day not found"));
    }
}
