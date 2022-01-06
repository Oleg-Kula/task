package com.gmail.kulacholeg.task.repository;

import com.gmail.kulacholeg.task.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedule,Long> {
}
