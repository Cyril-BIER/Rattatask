package com.cbi.taskManager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTestCase {
    @Test
    public void userConstructorTest(){
        User user = new User("username","password");
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
    }
}
