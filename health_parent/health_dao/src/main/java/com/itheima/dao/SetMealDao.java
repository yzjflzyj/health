package com.itheima.dao;


import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetMealDao {

    /**
     * 添加,设置套餐和检查组中间表
     * @param map
     */
    void setMealAndGroup(Map<String, Integer> map);

    /**
     * 添加,保存套餐信息
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 套餐,分页显示,根据条件查询
     * @param queryString
     * @return
     */
    Page<Setmeal> selectByCondition(String queryString);


    /**
     * 移动端,查询显示所有套餐
     * @return
     */
    List<Setmeal> findAllSetMeal();


    /**
     * 移动端,根据id查询套餐
     * @param id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 预约套餐占比
     * @return
     */
    List<Map<String, Object>> findSetmealCount();
}
