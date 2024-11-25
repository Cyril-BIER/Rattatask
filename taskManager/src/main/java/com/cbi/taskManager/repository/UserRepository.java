package com.cbi.taskManager.repository;

import com.cbi.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}