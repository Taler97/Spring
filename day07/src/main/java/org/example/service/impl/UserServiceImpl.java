package org.example.service.impl;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements org.example.service.inter.UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> searchUsers(String name, Integer age, Double minMoney, Double maxMoney) {
        return userMapper.selectByCondition(name, age, minMoney, maxMoney);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }



    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }
}