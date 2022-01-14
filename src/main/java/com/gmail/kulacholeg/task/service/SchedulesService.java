package com.gmail.kulacholeg.task.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gmail.kulacholeg.task.model.Schedule;
import com.gmail.kulacholeg.task.model.Subject;
import com.gmail.kulacholeg.task.repository.DaysRepository;
import com.gmail.kulacholeg.task.repository.GroupsRepository;
import com.gmail.kulacholeg.task.repository.SchedulesRepository;
import com.gmail.kulacholeg.task.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class SchedulesService {

    private final SchedulesRepository schedulesRepository;
    private final DaysRepository daysRepository;
    private final GroupsRepository groupsRepository;
    private final SubjectsRepository subjectsRepository;

    @Autowired
    public SchedulesService(SchedulesRepository schedulesRepository,
                            DaysRepository daysRepository,
                            GroupsRepository groupsRepository,
                            SubjectsRepository subjectsRepository){
        this.schedulesRepository = schedulesRepository;
        this.daysRepository = daysRepository;
        this.groupsRepository = groupsRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public List<Schedule> getAllSchedules(){
        return schedulesRepository.findAll();
    }

    public Schedule getOneSchedule(Long id){
        return schedulesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No schedule with id=" + id
        ));
    }

    public Schedule addSchedule(ObjectNode objectNode){
        Schedule schedule = new Schedule();
        return checkAndSave(objectNode, schedule);
    }

    public Schedule updateScheduleById(ObjectNode objectNode, Long id){
        return schedulesRepository.findById(id)
                .map(schedule -> checkAndSave(objectNode, schedule))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No schedule with id= " + id
                ));
    }

    public HttpStatus deleteScheduleById(Long id){
        Schedule schedule = schedulesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No schedule with id= " + id
                ));
        schedulesRepository.delete(schedule);
        return HttpStatus.OK;
    }

    //get schedule per student

    private Schedule checkAndSave(ObjectNode objectNode, Schedule schedule) {
        if(daysRepository.findById(objectNode.get("dayId").asLong()).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found");
        schedule.setDay(daysRepository.findById(objectNode.get("dayId").asLong()).get());

        if(groupsRepository.findById(objectNode.get("groupId").asLong()).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        schedule.setGroup(groupsRepository.findById(objectNode.get("groupId").asLong()).get());

        ArrayNode arrayNode =(ArrayNode) objectNode.get("subjects");
        Iterator<JsonNode> itr = arrayNode.elements();
        long id;
        Set<Subject> subjects = new HashSet<>();
        while (itr.hasNext()){
            id = itr.next().asLong();
            if(subjectsRepository.findById(id).isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no subject with id=" + id);
            subjects.add(subjectsRepository.getById(id));
        }
        schedule.setSubjects(subjects);
        return schedulesRepository.save(schedule);
    }
}
