package com.gmail.kulacholeg.task.repository;

import com.gmail.kulacholeg.task.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subject, Long> {
}
