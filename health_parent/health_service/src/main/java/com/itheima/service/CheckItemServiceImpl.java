package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


//在jdk的动态代理,事务的默认基于接口创建动态代理优先级高于创建service对象,因此用cglib的动态代理(基于类,不加配置会用spring的代理对象来创建,包不对)
////在dubbo2.6.1中已经解决这个问题

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService{
    /**
     * 检查项业务逻辑
     */
   @Autowired
   private CheckItemDao checkItemDao;


    /**
     * 新增checkitem数据
     */

   @Override
    public void add(CheckItem checkItem) {
       checkItemDao.add(checkItem);
    }

    /**
     * 分页显示
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页插件的固定方法
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckItem> page = checkItemDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 删除检查项
     * @param id
     */
    @Override
    public void delete(int id) {
        //查询当前检查项是否和检查组关联
        Integer count = checkItemDao.findCountByCheckItemId(id);
        if (count>0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项有关联.不可删除");
        }
        checkItemDao.delete(id);
    }

    /**
     * 编辑,查询检查项信息
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    /**
     * 编辑,更新检查项信息
     * @param checkItem
     */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    /**
     * 查询所有检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
