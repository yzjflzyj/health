package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {
    /**
     * 通过username得到user对象
     * @param username
     * @return
     */
    User findByUsername(String username);
}
