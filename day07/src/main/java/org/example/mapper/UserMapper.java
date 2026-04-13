package org.example.mapper;

import org.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {

    // 多条件查询
    List<User> selectByCondition(@Param("name") String name,
                                 @Param("age") Integer age,
                                 @Param("minMoney") Double minMoney,
                                 @Param("maxMoney") Double maxMoney);
    // 查询所有
    List<User> selectAll();

    // 新增
    int insert(User user);

    // 修改
    int update(User user);

    // 删除
    int deleteById(@Param("id") Integer id);
}