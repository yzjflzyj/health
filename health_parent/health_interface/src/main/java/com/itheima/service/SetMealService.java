package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetMealService {
    /**
     * 套餐,添加
     * @param checkgroupIds
     * @param setmeal
     */
    void add(Integer[] checkgroupIds, Setmeal setmeal);

    /**
     * 套餐,分页显示
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 移动端,查询所有套餐
     * @return
     */
    List<Setmeal> findAll();

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
    List<Map<String,Object>> findSetmealCount();
}
