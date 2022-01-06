package com.gmail.kulacholeg.task.service;

import com.gmail.kulacholeg.task.model.Classroom;
import com.gmail.kulacholeg.task.repository.ClassroomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClassroomsService {

    private final ClassroomsRepository repository;

    @Autowired
    public ClassroomsService(ClassroomsRepository repository) {
        this.repository = repository;
    }

    public List<Classroom> getAllClassrooms() {
        return repository.findAll();
    }

    public Classroom saveClassroom(Classroom classroom) {
        return repository.save(classroom);
    }

    public Classroom getClassroomById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Classroom not found"
        ));
    }

    public Classroom updateClassroom(Classroom newClassroom, Long id) {
        return repository.findById(id).map(classroom -> {
                    classroom.setNumber(newClassroom.getNumber());
                    return repository.save(classroom);
                }
        ).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Classroom not found"
        ));
    }

    public void deleteClassroom(Long id) {
        Classroom classroom = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Classroom not found"
                ));
        repository.delete(classroom);
    }
}
