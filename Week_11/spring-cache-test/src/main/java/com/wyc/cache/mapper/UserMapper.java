package com.wyc.cache.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wyc.cache.entity.User;

/**
 * @author yuchen.wu
 * @date 2021-01-03
 */
@Mapper
public interface UserMapper {

    User find(int id);

    List<User> list();

}
