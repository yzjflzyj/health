package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    /**
     * 批量上传预约设置
     * @param orderSettingList
     */
    void upload(List<OrderSetting> orderSettingList);

    /**
     * 预约设置通过日历控件展示
     * @param date
     * @return
     */
    List<Map> getOrderSettingByMonth(String date);

    /**
     * 页面设置预约人数
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);
}
