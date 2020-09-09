package com.thoughtworks.capacity.gtb.mvc.controller.dto;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRequest {

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^\\w{3,10}$", message = "用户名不合法")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 5, max = 12, message = "密码不合法")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱地址不合法")
    private String email;

    public static User toUser(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
