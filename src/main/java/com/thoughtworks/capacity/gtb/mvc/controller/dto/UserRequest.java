package com.thoughtworks.capacity.gtb.mvc.controller.dto;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRequest {

    private String username;

    private String password;

    private String email;

    public static User toUser(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
