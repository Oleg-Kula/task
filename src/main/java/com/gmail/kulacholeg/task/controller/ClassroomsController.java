package com.gmail.kulacholeg.task.controller;

import com.gmail.kulacholeg.task.model.Classroom;
import com.gmail.kulacholeg.task.service.ClassroomsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassroomsController {

    private final ClassroomsService classroomsService;

    public ClassroomsController(ClassroomsService service){
        this.classroomsService = service;
    }

    @PostMapping("/classrooms")
    public Classroom addClassroom(@RequestBody Classroom classroom){
        return classroomsService.saveClassroom(classroom);
    }

    @GetMapping("/classrooms")
    public List<Classroom> getAllClassrooms(){
        return classroomsService.getAllClassrooms();
    }

    @GetMapping("/classrooms/{id}")
    public Classroom getGroup(@PathVariable Long id){
        return classroomsService.getClassroomById(id);
    }

    @PutMapping("/classrooms/{id}")
    public Classroom updateClassroom(@RequestBody Classroom classroom,
                             @PathVariable Long id){
        return classroomsService.updateClassroom(classroom, id);
    }

    @DeleteMapping("/classrooms/{id}")
    public void deleteClassroom(@PathVariable Long id){
        classroomsService.deleteClassroom(id);
    }

}

