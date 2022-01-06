package com.gmail.kulacholeg.task.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gmail.kulacholeg.task.model.Student;
import com.gmail.kulacholeg.task.repository.GroupsRepository;
import com.gmail.kulacholeg.task.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final GroupsRepository groupsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository,
                           GroupsRepository groupsRepository){
        this.studentsRepository = studentsRepository;
        this.groupsRepository = groupsRepository;
    }

    public Student createStudent(ObjectNode objectNode){
        Student student = new Student();
        student.setFirstName(objectNode.get("firstName").asText());
        student.setSecondName(objectNode.get("secondName").asText());
        if(groupsRepository.findById(objectNode.get("groupId").asLong()).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        student.setGroup(groupsRepository.getById(objectNode.get("groupId").asLong()));
        return studentsRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentsRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"
                ));
    }

    public Student updateStudentById(String firstName, String secondName, Long groupId ,Long id){
        return studentsRepository.findById(id)
                .map(student -> {
                    student.setFirstName(firstName);
                    student.setSecondName(secondName);
                    if(groupsRepository.findById(groupId).isPresent())
                        student.setGroup(groupsRepository.getById(groupId));
                    else
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
                    return studentsRepository.save(student);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"
                ));
    }

    public void deleteStudent(Long id){
        Student student = studentsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"
                ));
        studentsRepository.delete(student);
    }
}
