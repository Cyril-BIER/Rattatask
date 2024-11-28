package com.cbi.taskManager.repository;

import com.cbi.taskManager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}