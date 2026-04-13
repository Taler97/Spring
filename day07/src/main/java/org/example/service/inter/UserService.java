package org.example.service.inter;

import org.example.entity.User;
import java.util.List;

public interface UserService {

    // 多条件查询
    List<User> searchUsers(String name, Integer age, Double minMoney, Double maxMoney);

    // 查询所有
    List<User> getAllUsers();


    // 新增
    void addUser(User user);

    // 修改
    void updateUser(User user);

    // 删除
    void deleteUser(Integer id);
}