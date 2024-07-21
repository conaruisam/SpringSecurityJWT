package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {


    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin(){

        return "Hola, has accedido con rol de ADMIN";
    }

    @GetMapping("/accessUser")
    @PreAuthorize("hasRole('USER')")
    public String accessUser(){

        return "Hola, has accedido con rol de USER";
    }
    @GetMapping("/accessGuest")
    @PreAuthorize("hasRole('GUEST')")
    public String accessGuest(){

        return "Hola, has accedido con rol de GUEST";
    }
}
