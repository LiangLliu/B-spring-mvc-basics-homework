package com.thoughtworks.capacity.gtb.mvc.repository;


import com.thoughtworks.capacity.gtb.mvc.domain.User;

public interface UserRepository {
    User save(User user);

    boolean isExisted(String username);

    User findUserByUserName(String username);
}
