package com.shark.community.mapper;

import com.shark.community.model.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by shark on 2020/5/28 10:19
 */
@Mapper
public interface userMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(user user);
}
