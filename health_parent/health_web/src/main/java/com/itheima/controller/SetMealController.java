package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;


import java.util.UUID;

@RestController
@RequestMapping("/SetMeal")
public class SetMealController {

    @Reference
    private SetMealService setMealService;
    //Jedis操作redis客户端对象
    @Autowired
    private JedisPool jedisPool;

    /**
     * 添加套餐
     * @param checkgroupIds
     * @param setmeal
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(Integer[] checkgroupIds, @RequestBody Setmeal setmeal) {
        try {
            setMealService.add(checkgroupIds, setmeal);
            //套餐上传数据库后将图片名称保存到set2
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    /**
     * 套餐,分页显示
     * @param queryPageBean
     * @return
     */

    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = null;
        try {
            pageResult = setMealService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageResult;
    }

    /**
     * 图片上传
     * @param imgFile
     * @return
     */
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam MultipartFile imgFile) {
        try {
            //1.文件名处理 xxxxx.jpg===>uuid.jpg
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            //获取后缀
            // spring框架获取文件后缀名
            // String suffix =StringUtils.getFilenameExtension(originalFilename);
            int index = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(index);//后缀
            //重新命名
            String newFileName = UUID.randomUUID().toString() + suffix;

            //2.用七牛云上传
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),newFileName);

            //3.将上传七牛云后图片名称保存到set1
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,newFileName);
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
}
