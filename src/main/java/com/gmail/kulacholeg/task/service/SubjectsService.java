package com.gmail.kulacholeg.task.service;

import com.gmail.kulacholeg.task.model.Subject;
import com.gmail.kulacholeg.task.repository.ClassroomsRepository;
import com.gmail.kulacholeg.task.repository.SubjectsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubjectsService {

    private final SubjectsRepository subjectsRepository;
    private final ClassroomsRepository classroomsRepository;

    public SubjectsService(SubjectsRepository subjectsRepository,
                           ClassroomsRepository classroomsRepository) {
        this.subjectsRepository = subjectsRepository;
        this.classroomsRepository= classroomsRepository;
    }

    public Subject createSubject(Subject subject) {
        return subjectsRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectsRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Subject not found"
                ));
    }

    public Subject updateSubjectById(String name, Long classroomId, Long id) {
        return subjectsRepository.findById(id)
                .map(subject -> {
                    subject.setName(name);
                    if (classroomsRepository.findById(classroomId).isPresent())
                        subject.setClassroom(classroomsRepository.getById(classroomId));
                    else
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom not found");
                    return subjectsRepository.save(subject);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Subject not found"
                ));
    }

    public void deleteSubject(Long id){
        Subject subject = subjectsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Subject not found"
                ));
        subjectsRepository.delete(subject);
    }
}
