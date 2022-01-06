package com.gmail.kulacholeg.task.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gmail.kulacholeg.task.model.Student;
import com.gmail.kulacholeg.task.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService){
        this.studentsService = studentsService;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody ObjectNode objectNode){
        return studentsService.createStudent(objectNode);
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentsService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentsService.getStudentById(id);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestBody ObjectNode objectNode,
                                 @PathVariable Long id){
        return studentsService.updateStudentById(objectNode.get("firstName").asText(),
                                                 objectNode.get("secondName").asText(),
                                                 objectNode.get("groupId").asLong(),
                                                 id);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudents(@PathVariable Long id){
        studentsService.deleteStudent(id);
    }
}
