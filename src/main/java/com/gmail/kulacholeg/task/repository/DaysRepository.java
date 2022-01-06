package com.gmail.kulacholeg.task.repository;

import com.gmail.kulacholeg.task.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaysRepository extends JpaRepository<Day,Long> {
}
