package com.cbi.taskManager.model;

import com.cbi.taskManager.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTestCase {
    @Test
    public void constructorTest(){
        Task task = new Task("Conception", "Concevoir le jeu de données", TaskStatus.TODO);
        assertEquals("Conception", task.getName());
        assertEquals("Concevoir le jeu de données", task.getDescription());
        assertEquals(TaskStatus.TODO,task.getStatus());
    }

    @Test
    public void equalsTest(){
        Task tache1 = new Task("tâche", "Description", TaskStatus.DONE);
        Task tache2 = new Task("tâche", "Description", TaskStatus.DONE);
        assertEquals(tache1, tache2);
    }
}
