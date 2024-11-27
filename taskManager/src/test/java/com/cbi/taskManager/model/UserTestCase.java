package com.cbi.taskManager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTestCase {
    @Test
    public void userConstructorTest(){
        User user = new User("email","password");
        assertEquals("email", user.getUsername());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void compareUserTest(){
        assertEquals(
                new User("user","pass"),
                new User("user", "pass")
        );
    }
}
