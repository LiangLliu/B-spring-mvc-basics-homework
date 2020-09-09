package com.thoughtworks.capacity.gtb.mvc.repository.impl;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        user.setId(users.size() + 1);
        users.put(user.getUsername(), user);
        return user;
    }
}
