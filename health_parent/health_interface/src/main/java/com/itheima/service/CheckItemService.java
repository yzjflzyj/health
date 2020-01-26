package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    /**
     * 检查项接口层
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 检查项分页
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     * @param id
     */
    void delete(int id);

    /**
     * 编辑检查项,查询信息
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**
     * 编辑检查项,更新
     * @param checkItem
     */
    void update(CheckItem checkItem);


    /**
     * 查询所有检查项
     * @return
     */
    List<CheckItem> findAll();
}
