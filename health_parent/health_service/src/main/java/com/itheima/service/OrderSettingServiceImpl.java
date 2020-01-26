package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * 批量上传预约设置
     *
     * @param orderSettingList
     */
    @Override
    public void upload(List<OrderSetting> orderSettingList) {
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                int count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                //判断日期是否存在
                if (count > 0) {
                    //存在即更新
                    orderSettingDao.update(orderSetting);
                } else {
                    //不存在则添加
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    /**
     * 预约设置通过日历控件展示
     *
     * @param date
     * @return
     */
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        //1.获取依据日期查询的正确格式
        String startDate = date + "-01";
        String endDate = date + "-31";
        Map<String,String> dateMap = new HashMap<>();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        //2.查询得到orderSettingList
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(dateMap);
        //3.将结果转换,放进List<Map>
        List<Map> mapList = new ArrayList<>();
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                Map<String, Object> map = new HashMap<>();
                map.put("date",orderSetting.getOrderDate().getDate());
                map.put("number",orderSetting.getNumber());
                map.put("reservations",orderSetting.getReservations());
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 页面设置预约人数
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
            int count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            //判断日期是否存在
            if (count > 0) {
                //存在即更新
                orderSettingDao.update(orderSetting);
            } else {
                //不存在则添加
                orderSettingDao.add(orderSetting);
            }

    }
}
