package com.itheima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @Reference
    private SetMealService setMealService;

    /**
     * 移动端,查询显示所有套餐
     * @return
     */
    @RequestMapping(value = "/getSetmeal", method = RequestMethod.POST)
    public Result getSetmeal() {
        try {
            List<Setmeal> setmealList = setMealService.findAll();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,setmealList);
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }

    /**
     * 移动端,根据id查询套餐
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Result findById(int id){
        try {
            Setmeal setmeal= setMealService.findById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }


}

