package org.example.service;


import org.example.entity.User;

import java.util.List;

public interface UserService {
    int add(User user);
    int update(User user);
    int delete(Integer id);
    User get(Integer id);
    List<User> list();
}
