package com.cbi.taskManager.model;

import com.cbi.taskManager.enums.TaskStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name ;
    private String description;
    private TaskStatus status = TaskStatus.TODO;
    @ManyToMany
    private List<User> users;

    public Task(String name, String description, List<User> users) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.users = users;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status && Objects.equals(users, task.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, users);
    }
}
