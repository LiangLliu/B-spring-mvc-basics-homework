package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.controller.dto.UserRequest;
import com.thoughtworks.capacity.gtb.mvc.controller.dto.UserResponse;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    public ResponseEntity<UserResponse> userLogin(@RequestParam("username")
                                                  @Pattern(regexp = "^\\w{3,10}$", message = "用户名不合法")
                                                  @NotBlank(message = "用户名不能为空")
                                                          String username,

                                                  @RequestParam("password")
                                                  @NotBlank(message = "密码不能为空")
                                                  @Size(min = 5, max = 12, message = "密码不合法")
                                                          String password) {
        UserResponse userResponse = userService.login(username, password);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }
}
