package com.example.mycontact.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWorldControllerTest {
    @Autowired
    private HelloWorldController helloWorldController;

    @Test
    void helloWorld(){
        System.out.println(helloWorldController.helloWorld());
    }
}