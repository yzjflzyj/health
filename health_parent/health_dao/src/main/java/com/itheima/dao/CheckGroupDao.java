package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    /**
     * 检查组添加
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 设置检查组和检查项中间表
     * @param map
     */
    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    /**
     * 检查组分页显示
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(String queryString);


    /**
     * 编辑,查询检查组基本信息
     * @param groupId
     * @return
     */
    CheckGroup findById(Integer groupId);


    /**
     * 编辑,查询检查项列表复选框数组
     * @param groupId
     * @return
     */
    Integer[] findCheckItemIds(Integer groupId);


    /**
     * 编辑,更新检查组
     * @param checkGroup
     */
    void updateGroup(CheckGroup checkGroup);

    /**
     * 编辑,删除已有的中间表信息
     * @param id
     */
    void deleteAssociation(Integer id);

    /**
     * 增添,查询检查组全部信息
     * @return
     */
    List<CheckGroup> findAll();
}
