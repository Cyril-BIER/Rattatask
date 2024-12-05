package com.cbi.taskManager.model;

import com.cbi.taskManager.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTestCase {
    @Test
    public void constructor0UserTest(){
        Task task = new Task("Conception", "Concevoir le jeu de données", List.of());
        assertEquals("Conception", task.getName());
        assertEquals("Concevoir le jeu de données", task.getDescription());
        assertEquals(TaskStatus.TODO,task.getStatus());
        assertEquals(List.of(), task.getUsers());
    }

    @Test
    public void constructor1UserTest(){
        List<User> users = List.of(new User("mail","name", "lastname", "password"));
        Task task = new Task("Conception", "Concevoir le jeu de données", users);
        assertEquals(users, task.getUsers());
    }

    @Test
    public void constructorManyUserTest(){
        List<User> users = List.of(
                new User("mail1","name", "lastname", "password"),
                new User("mail2", "name", "lastname","password"),
                new User("mail3","name", "lastname", "password")
                );
        Task task = new Task("Conception", "Concevoir le jeu de données", users);
        assertEquals(users, task.getUsers());
    }

    @Test
    public void equalsTest(){
        Task tache1 = new Task("tâche", "Description", List.of());
        Task tache2 = new Task("tâche", "Description", List.of());
        assertEquals(tache1, tache2);
    }
}
