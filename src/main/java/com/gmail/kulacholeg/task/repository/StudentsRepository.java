package com.gmail.kulacholeg.task.repository;

import com.gmail.kulacholeg.task.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

}
