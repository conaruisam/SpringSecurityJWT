package com.example.controller;

import com.example.entity.ERole;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repositories.UserRepository;
import com.example.request.CreateUserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World Not Secured";
    }
    @GetMapping("/helloSecured")
    public String helloSecured() {
        return "Hello World Secured";
    }

    @PostMapping("/createUser")
    // El valid es que se valide que los campos sean correctos.
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO requestDTO) {

        Set<RoleEntity> roles = requestDTO.getRoles().stream()
                .map(rol -> RoleEntity.builder()
                            .name(ERole.valueOf(rol))
                            .build()
                ).collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .email(requestDTO.getEmail())
                //.roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    // El valid es que se valide que los campos sean correctos.
    public String deleteUser(@Valid String id) {

        userRepository.deleteById(Long.valueOf(id));

        return "Se ha borrado con éxito el usuario.";
    }
}
