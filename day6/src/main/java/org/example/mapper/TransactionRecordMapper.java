package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.entity.TransactionRecord;

import java.util.List;

@Mapper
public interface TransactionRecordMapper {

    /**
     * 插入交易记录
     */
    @Insert("INSERT INTO transaction_record(account, update_time, category, num) " +
            "VALUES(#{account}, #{updateTime}, #{category}, #{num})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(TransactionRecord record);

    /**
     * 查询某用户的所有交易记录
     */
    @Select("SELECT id, account, update_time, category, num " +
            "FROM transaction_record " +
            "WHERE account = #{account} " +
            "ORDER BY update_time DESC")
    @Results(id = "recordResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "account", column = "account"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "category", column = "category"),
            @Result(property = "num", column = "num")
    })
    List<TransactionRecord> selectByAccount(@Param("account") String account);
}