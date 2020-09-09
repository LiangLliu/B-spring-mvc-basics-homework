package com.thoughtworks.capacity.gtb.mvc.controller;


import com.thoughtworks.capacity.gtb.mvc.controller.dto.UserRequest;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void addUser(@RequestBody UserRequest request) {
        userService.save(request);
    }

}