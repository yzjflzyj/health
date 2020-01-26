package com.itheima.dao;

import com.itheima.pojo.User;

public interface UserDao {
    /**
     * 通过username得到user对象
     * @param username
     * @return
     */
    User findByUsername(String username);
}
