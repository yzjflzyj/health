package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.SetMealDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealDao setMealDao;

    /**
     * 增加套餐
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        //保存套餐基本信息
        setMealDao.add(setmeal);
        //得到套餐id
        Integer mealId = setmeal.getId();
        //设置套餐和检查组中间表
        setMealAndGroup(mealId, checkgroupIds);
    }

    /**
     * 套餐,分页显示
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page=setMealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 移动端,查询显示所有套餐
     * @return
     */
    @Override
    public List<Setmeal> findAll() {
        return setMealDao.findAllSetMeal();
    }

    /**
     * 移动端,根据id查询套餐
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(int id) {
        return setMealDao.findById(id);
    }

    /**
     * 预约套餐占比
     * @return
     */
    @Override
    public List<Map<String,Object>> findSetmealCount() {
        return setMealDao.findSetmealCount();
    }


    /**
     * 设置套餐和检查组中间表
     * @param mealId
     * @param checkgroupIds
     */
    private void setMealAndGroup(Integer mealId, Integer[] checkgroupIds) {
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            Map<String, Integer> map = new HashMap();
            for (Integer checkgroupId : checkgroupIds) {
                map.put("checkgroupId", checkgroupId);
                map.put("mealId", mealId);
                setMealDao.setMealAndGroup(map);
            }
        }
    }
}
