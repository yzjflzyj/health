package com.itheima.controller;


import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 预约生成验证码
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/send4Order", method = RequestMethod.POST)
    public Result send4Order(String telephone) {
        //如果已经发送过验证码，返回验证码操作过于频繁

        //1.发送验证码checkCode
        String checkCode = ValidateCodeUtils.generateValidateCode4String(6);
        try {
            //2.调用阿里云短信接口
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, checkCode);
            //3.将验证码存入redis
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 5 * 60, checkCode);
            System.out.println("手机号码：：：：："+telephone+"::::::"+checkCode);
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }


    /**
     * 手机号快速登录验证码
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/send4Login", method = RequestMethod.POST)
    public Result send4Login(String telephone) {
        //如果已经发送过验证码，返回验证码操作过于频繁

        //1.发送验证码checkCode
        String checkCode = ValidateCodeUtils.generateValidateCode4String(6);
        try {
            //2.调用阿里云短信接口
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, checkCode);
            //3.将验证码存入redis
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN, 5 * 60, checkCode);
            System.out.println("手机号码：：：：："+telephone+"::::::"+checkCode);
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }



}
