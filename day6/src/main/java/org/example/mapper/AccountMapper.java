package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.entity.Account;

import java.util.List;


@Mapper
public interface AccountMapper {
    /**
     * 根据账号查询用户
     */
    @Select("SELECT id, account, password, coupon FROM account WHERE account = #{account}")
    @Results(id = "accountResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "account", column = "account"),
            @Result(property = "password", column = "password"),
            @Result(property = "coupon", column = "coupon")
    })
    Account selectByAccount(@Param("account") String account);


    /**
     * 插入新用户
     */
    @Insert("INSERT INTO account(account, password, coupon) VALUES(#{account}, #{password}, #{coupon})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Account account);



    /**
     * 更新用户点券（加减操作）
     * @param account 账号
     * @param delta 变化量（正数增加，负数减少）
     */
    @Update("UPDATE account SET coupon = coupon + #{delta} WHERE account = #{account}")
    int updateCoupon(@Param("account") String account, @Param("delta") int delta);

    /**
     * 查询所有用户
     */
    @Select("SELECT id, account, password, coupon FROM account ORDER BY id")
    @ResultMap("accountResultMap")
    List<Account> selectAll();

    /**
     * 登录验证
     */
    @Select("SELECT id, account, password, coupon FROM account WHERE account = #{account} AND password = #{password}")
    @ResultMap("accountResultMap")
    Account login(@Param("account") String account, @Param("password") String password);
}
