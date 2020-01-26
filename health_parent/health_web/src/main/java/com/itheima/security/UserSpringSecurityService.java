package com.itheima.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserSpringSecurityService implements UserDetailsService {

    @Reference
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询user对象(这是pojo中的user对象,需要在其中封装role的对象集合,role的对象中又封装permission对象)
        com.itheima.pojo.User user = userService.findByUsername(username);
        if (user == null) {
            return null;
        }
        //根据user找到role对象
        Set<Role> roles = user.getRoles();
        //定义一个list集合用于存放权限
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(role.getKeyword()));
                Set<Permission> permissions = role.getPermissions();
                if (permissions != null) {
                    for (Permission permission : permissions) {
                        grantedAuthorityList.add(new SimpleGrantedAuthority(permission.getKeyword()));
                    }
                }
            }
        }

        UserDetails userDetails=new User(username,user.getPassword(),grantedAuthorityList);
        //交给spring security框架
        return userDetails;
    }
}
