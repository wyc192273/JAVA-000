package com.wyc.rpcfx.provider.service.impl;

import com.wyc.rpcfx.user.api.bean.User;
import com.wyc.rpcfx.user.api.service.UserService;

/**
 * Created by yuchen.wu on 2020-12-14
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findUserById(int id) {
        return new User(1, "wyc");
    }
}
