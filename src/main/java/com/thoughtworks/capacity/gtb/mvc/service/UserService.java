package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.controller.dto.UserRequest;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserIsExistException;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserRequest request) {

        String username = request.getUsername();
        if (userRepository.isExisted(username)) {
            throw new UserIsExistException("用户已存在");
        }
        User user = UserRequest.toUser(request);
        userRepository.save(user);
    }
}
