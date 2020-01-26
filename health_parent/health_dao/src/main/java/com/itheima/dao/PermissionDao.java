package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    /**
     * 通过role的id得到permission的集合
     * @param id
     * @return
     */
    Set<Permission> findByRoleId(Integer id);
}
