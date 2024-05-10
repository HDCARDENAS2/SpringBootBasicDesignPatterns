package com.learn.desingpatterns.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.desingpatterns.dto.UserDTO;
import com.learn.desingpatterns.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
@Log4j2
public class UserController {

    private final UserService userService;

    @ApiOperation("Save a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User saved successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        log.info("Entering saveUser method with userDTO: {}", userDTO);
        UserDTO savedUser = userService.save(userDTO);
        log.info("Exiting saveUser method with savedUser: {}", savedUser);
        return ResponseEntity.ok(savedUser);
    }

    @ApiOperation("Find user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 204, message = "No Content")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Integer id) {
        log.info("Entering findUserById method with id: {}", id);
        UserDTO userDTO = userService.findById(id);
        log.info("Exiting findUserById method with userDTO: {}", userDTO);
        return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.noContent().build();
    }

    @ApiOperation("Find all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users found"),
            @ApiResponse(code = 204, message = "No Content")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        log.info("Entering findAllUsers method");
        List<UserDTO> users = userService.findAll();
        log.info("Exiting findAllUsers method with users: {}", users);
        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
    }

    @ApiOperation("Find users created today")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users found"),
            @ApiResponse(code = 204, message = "No Content")
    })
    @GetMapping("/created-today")
    public ResponseEntity<List<UserDTO>> findUsersCreatedToday() {
        log.info("Entering findUsersCreatedToday method");
        List<UserDTO> users = userService.findUsersCreatedToday();
        log.info("Exiting findUsersCreatedToday method with users: {}", users);
        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
    }

    //TODO create api method GET findUsersCreatedByYear(Integer year)
    @ApiOperation("Find users created by year")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users found"),
            @ApiResponse(code = 204, message = "No Content")
    })
    @GetMapping("/created-year/{year}")
    public ResponseEntity<List<UserDTO>> findUsersCreatedByYear(@PathVariable(name = "year") Integer year) {
        log.info("Entering findUsersCreatedByYear method");
        List<UserDTO> users = userService.findUsersCreatedByYear(year);
        log.info("Exiting findUsersCreatedByYear method with users: {}", users);
        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
    }
}
