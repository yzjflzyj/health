package com.itheima.dao;

import com.itheima.pojo.Role;

import java.util.Set;

public interface RoleDao {
    /**
     * 通过user的id得到role的集合
     * @param id
     * @return
     */
    Set<Role> findByUserId(Integer id);
}
