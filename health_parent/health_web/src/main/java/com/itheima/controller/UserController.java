package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户名
     * @return
     */
    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public Result getUsername() {
        //通过spring security框架中提供SecurityContextHolder来获取用户对象
        //SecurityContext 安全容器对象
        // 通过容器安全对象 获取 Authentication认证 ,
        // 最终通过Authentication认证获取用户对象principal
        //无法获得密码,但是shiro可以
        User principal =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();//获取用户名
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,username);
    }
}
