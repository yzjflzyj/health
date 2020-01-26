package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {

    /**
     * 批量上传预约设置和页面设置人数,判断日期是否存在
     * @param orderDate
     * @return
     */
    int findCountByOrderDate(Date orderDate);


    /**
     * 批量上传预约设置和页面设置,日期存在即更新
     * @param orderSetting
     */
    void update(OrderSetting orderSetting);


    /**
     * 批量上传预约设置和页面设置,日期不存在即添加
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);


    /**
     * 预约设置通过日历控件展示
     * @param dateMap
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(Map<String, String> dateMap);

    /**
     * 根据预约时间查询预约设置对象
     * @param orderDate2
     * @return
     */
    OrderSetting findByOrderDate(Date orderDate2);


    /**
     * 根据预约时间修改已经预约人数
     * @param orderSetting
     */
    void editReservationsByOrderDate(OrderSetting orderSetting);

}
