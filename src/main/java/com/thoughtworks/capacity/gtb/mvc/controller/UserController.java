package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.controller.dto.UserRequest;
import com.thoughtworks.capacity.gtb.mvc.controller.dto.UserResponse;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody @Valid UserRequest request) {
        userService.save(request);
    }


    @GetMapping("/login")
    public ResponseEntity<UserResponse> userLogin(@RequestParam("username") String username,
                                                  @RequestParam("password") String password) {
        UserResponse userResponse = userService.login(username, password);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }
}
