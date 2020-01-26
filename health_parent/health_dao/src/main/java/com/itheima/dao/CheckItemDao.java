package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
     /**
      * 添加检查项
      * @param checkItem
      */
     void add(CheckItem checkItem);


     /**
      * 检查项分页
      * @param queryString
      * @return
      */
     Page<CheckItem> selectByCondition(String queryString);


     /**
      * 删除检查项
      * @param id
      */

     Integer findCountByCheckItemId(int id);
     void delete(int id);

     /**
      * 编辑,查询检查项信息
      * @param id
      * @return
      */
     CheckItem findById(Integer id);

     /**
      * 编辑,更新检查项信息
      * @param checkItem
      */
     void update(CheckItem checkItem);

     /**
      * 查询所有检查项
      * @return
      */
     List<CheckItem> findAll();
}
