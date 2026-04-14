package org.example.service.impl;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> list() {
        return userMapper.selectAll();
    }
}