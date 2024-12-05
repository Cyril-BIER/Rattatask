package com.cbi.taskManager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTestCase {
    @Test
    public void userConstructorTest(){
        User user = new User("email","name","lastname","password");
        assertEquals("email", user.getUsername());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void compareUserTest(){
        assertEquals(
                new User("user","name", "lastname","pass"),
                new User("user","name", "lastname", "pass")
        );
    }
}
