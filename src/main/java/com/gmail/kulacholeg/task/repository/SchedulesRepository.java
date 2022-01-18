package com.gmail.kulacholeg.task.repository;

import com.gmail.kulacholeg.task.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedule,Long> {

    @Query(value = "select sub.name, c.number from subjects sub " +
            "join schedules_subjects ss on sub.id = ss.subject_id " +
            "join schedules s on ss.schedule_id = s.id " +
            "join days d on s.day_id = d.id " +
            "join groups g on s.group_id = g.id " +
            "join students s2 on g.id = s2.group_id " +
            "join classrooms c on sub.classroom_id = c.id " +
            "where d.id = ?1 and s2.id = ?2", nativeQuery = true)
    List<String> findSchedulesByStudentAndDay(Long dayId, Long studentId);
}
