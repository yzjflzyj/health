package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkitemIds
     * @param checkGroup
     */
    void add(Integer [] checkitemIds,CheckGroup checkGroup);

    /**
     * 检查组分页显示
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

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
     * 编辑,更新检查组及检查项列表信息
     * @param checkGroup
     * @param checkitemIds
     */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 增添,查询检查组全部信息
     * @return
     */
    List<CheckGroup> findAll();
}
