package com.wyc.cache.service;

import java.util.List;


import org.springframework.cache.annotation.CacheConfig;

import com.wyc.cache.entity.User;

@CacheConfig(cacheNames = "users")
public interface UserService {

    User find(int id);

    List<User> list();

}
