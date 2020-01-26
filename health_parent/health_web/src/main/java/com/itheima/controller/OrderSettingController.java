package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 批量上传预约设置
     * @param excelFile
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(@RequestParam MultipartFile excelFile){
        try {
            //1.拿到读取的数据集合
            List<String[]> list = POIUtils.readExcel(excelFile);
            //list中的元素为一行读到的数组数据,即是一个ordersetting所需的数据
            if (list != null && list.size()>0){
                List<OrderSetting> orderSettingList=new ArrayList<>();
                for (String[] strings : list) {
                    OrderSetting orderSetting =new OrderSetting(new Date(strings[0]),Integer.parseInt(strings[1]));
                    orderSettingList.add(orderSetting);
                }
            //2.将读到的数据存到数据库
            orderSettingService.upload(orderSettingList);
            }
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    /**
     * 预约设置通过日历控件展示
     * @param date
     * @return
     */
    @RequestMapping(value = "/getOrderSettingByMonth",method = RequestMethod.POST)
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> list =orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.GET_ORDERSETTING_FAIL);
        }

    }

    /**
     * 页面设置预约人数
     * @param orderSetting
     * @return
     */
    @RequestMapping(value = "/editNumberByDate",method = RequestMethod.POST)
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
          orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.ORDERSETTING_FAIL);
        }

    }

}
