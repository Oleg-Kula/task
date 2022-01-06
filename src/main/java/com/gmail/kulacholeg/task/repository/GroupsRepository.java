package com.gmail.kulacholeg.task.repository;

import com.gmail.kulacholeg.task.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Group, Long> {
}
