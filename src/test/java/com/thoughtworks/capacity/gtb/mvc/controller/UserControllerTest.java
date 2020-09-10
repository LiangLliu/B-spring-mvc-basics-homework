package com.thoughtworks.capacity.gtb.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.controller.dto.UserRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Nested
    public class RegisterTest {
        @Test
        public void should_create_user_when_given_user_info() throws Exception {

            String url = "/register";

            UserRequest userRequest = UserRequest.builder()
                    .username("Tom")
                    .password("123456")
                    .email("a@qq.com")
                    .build();
            String request = objectMapper.writeValueAsString(userRequest);

            mockMvc.perform(post(url)
                    .content(request)
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isCreated());

        }


        @Test
        public void should_throw_exception_code_400_when_given_user_name_error() throws Exception {

            UserRequest userRequest = UserRequest.builder()
                    .username("Tom.")
                    .password("123456")
                    .email("a@qq.com")
                    .build();

            registerUserPostTest(userRequest);

        }

        @Test
        public void should_throw_exception_code_400_when_given_password_is_short() throws Exception {

            UserRequest userRequest = UserRequest.builder()
                    .username("Tom")
                    .password("12")
                    .email("a@qq.com")
                    .build();

            registerUserPostTest(userRequest);

        }


        @Test
        public void should_throw_exception_code_400_when_given_email_is_error() throws Exception {

            UserRequest userRequest = UserRequest.builder()
                    .username("Tom")
                    .password("1212234")
                    .email("qq.com")
                    .build();

            registerUserPostTest(userRequest);
        }


        public void registerUserPostTest(UserRequest userRequest) throws Exception {

            String url = "/register";
            String request = objectMapper.writeValueAsString(userRequest);
            mockMvc.perform(post(url)
                    .content(request)
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isBadRequest());
        }

    }


    @Nested
    public class LoginTest {
        @Test
        public void should_return_user_info_when_user_info_is_true() throws Exception {

            UserRequest userRequest = UserRequest.builder()
                    .username("Tom_login")
                    .password("123456")
                    .email("a@qq.com")
                    .build();
            String request = objectMapper.writeValueAsString(userRequest);

            mockMvc.perform(post("/register")
                    .content(request)
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isCreated());

            String url = "/login?username=" + userRequest.getUsername() + "&password=" + userRequest.getPassword();
            mockMvc.perform(get(url))
                    .andExpect(jsonPath("$.username", is(userRequest.getUsername())))
                    .andExpect(status().isOk());

        }

        @Test
        public void should_throw_error_code_404_when_user_info_is_not_exist() throws Exception {

            String url = "/login?username=user_not&password=1232324";
            mockMvc.perform(get(url))
                    .andExpect(status().isNotFound());

        }
    }


}