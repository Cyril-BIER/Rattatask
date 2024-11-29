package com.cbi.taskManager.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTestCase {
    @Test
    public void constructorTest(){
        Project project = new Project("Project 1");
        assertEquals("Project 1", project.getName());
        assertEquals(List.of(), project.getTasks());
    }

    @Test
    public void add1TaskTest(){
        List<Task> tasks = List.of(
                new Task("Nom tâche", "Description", List.of())
        );
        Project project = new Project("Project");
        project.addTasks(tasks);
        assertEquals(tasks, project.getTasks());
    }

    @Test
    public void addManyTasksTest(){
        List<Task> tasks = List.of(
                new Task("Nom tâche", "Description", List.of()),
                new Task("Nom tâche2", "Description", List.of()),
                new Task("Nom tâche3", "Description", List.of())
        );
        Project project = new Project("Project");
        project.addTasks(tasks);
        assertEquals(tasks, project.getTasks());
    }

    @Test
    public void compareTest(){
        Project project1 = new Project("My Project");
        Project project2 = new Project("My Project");

        assertEquals(project1, project2);
    }
}
