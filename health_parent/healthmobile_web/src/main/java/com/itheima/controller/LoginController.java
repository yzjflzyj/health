package com.itheima.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Result login(HttpServletResponse response, @RequestBody Map map) {

        //1.校验验证码 redis跟页面
        String validateCode = (String) map.get("validateCode");
        String telephone = (String) map.get("telephone");
        String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        //校验验证码
        if (StringUtils.isEmpty(validateCode) || StringUtils.isEmpty(redisCode) || !validateCode.equals(redisCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        //2.是否会员,是会员放进cookie,不是会员自动注册会员,放进cookie
        Member member = memberService.findByTelephone(telephone);
        if (member == null) {
            //会员不存在
            member = new Member();
            member.setRegTime(new Date());
            member.setPhoneNumber(telephone);
            memberService.add(member);
        }

       Cookie cookie = new Cookie(Member.LOGIN_MEMBER_TELEPHONE,telephone);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);
        return new Result(true,MessageConstant.LOGIN_SUCCESS);

    }


}

