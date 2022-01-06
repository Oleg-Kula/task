package com.gmail.kulacholeg.task.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gmail.kulacholeg.task.model.Subject;
import com.gmail.kulacholeg.task.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectsController {

    private final SubjectsService subjectsService;

    @Autowired
    public SubjectsController(SubjectsService subjectsService){
        this.subjectsService = subjectsService;
    }

    @GetMapping("/subjects")
    public List<Subject> getSubjects(){
        return subjectsService.getAllSubjects();
    }

    @PostMapping("/subjects")
    public Subject addSubject(@RequestBody Subject subject){
        return subjectsService.createSubject(subject);
    }

    @GetMapping("/subjects/{id}")
    public Subject getSubjectById(@PathVariable Long id){
        return subjectsService.getSubjectById(id);
    }

    @PutMapping("/subjects/{id}")
    public Subject updateSubject(@RequestBody ObjectNode node,
                                 @PathVariable Long id){
       return subjectsService.updateSubjectById(node.get("name").asText(),
                                                node.get("classroomId").asLong(),
                                                id);
    }

    @DeleteMapping("/subjects/{id}")
    public void deleteSubject(@PathVariable Long id){
        subjectsService.deleteSubject(id);
    }

}
