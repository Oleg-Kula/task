package com.gmail.kulacholeg.task.repository;

import com.gmail.kulacholeg.task.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomsRepository extends JpaRepository<Classroom, Long> {
}
