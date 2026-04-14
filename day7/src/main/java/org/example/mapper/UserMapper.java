package org.example.mapper;

import org.example.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserMapper {
    int insert(User user);
    int updateById(User user);
    int deleteById(Integer id);
    User selectById(Integer id);
    List<User> selectAll();
}