package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        //得到对象
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        //得到角色集合,并封装在user对象中
        Set<Role> roles = roleDao.findByUserId(user.getId());
        if (roles != null && roles.size() > 0) {
            user.setRoles(roles);
        }
        for (Role role : roles) {
            //得到permission集合,并封装在role对象中
            Set<Permission> permissions = permissionDao.findByRoleId(role.getId());
            if (permissions != null && permissions.size() > 0) {
                role.setPermissions(permissions);
            }
        }
        return user;
    }
}
