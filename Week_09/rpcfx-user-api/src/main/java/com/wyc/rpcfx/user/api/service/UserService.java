package com.wyc.rpcfx.user.api.service;

import com.wyc.rpcfx.user.api.bean.User;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public interface UserService {

    User findUserById(int id);

}
