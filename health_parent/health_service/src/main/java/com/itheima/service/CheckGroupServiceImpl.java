package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 增添检查组
     *
     * @param checkitemIds
     * @param checkGroup
     */
    @Override
    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
        //保存检查组表
        checkGroupDao.add(checkGroup);
        //得到检查组id
        Integer groupId = checkGroup.getId();
        //设置检查组和检查项中间表
        setCheckGroupAndCheckItem(groupId, checkitemIds);
    }



    /**
     * 分页显示
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页插件的固定方法
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckItem> page = checkGroupDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 编辑,显示检查组基本信息
     *
     * @param groupId
     * @return
     */
    @Override
    public CheckGroup findById(Integer groupId) {
        return checkGroupDao.findById(groupId);

    }

    /**
     * 编辑,查询检查项列表复选框数组
     *
     * @param groupId
     * @return
     */
    @Override
    public Integer[] findCheckItemIds(Integer groupId) {
        return checkGroupDao.findCheckItemIds(groupId);
    }


    /**
     * 编辑,更新检查组及检查项列表信息
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        //更新检查组基本信息
        checkGroupDao.updateGroup(checkGroup);
        //删除已有的检查组和检查项中间表
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //设置检查组和检查项中间表
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);

    }

    /**
     * 增添,查询检查组全部信息
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }


    /**
     * 设置检查组和检查项中间表
     *
     * @param groupId
     * @param checkitemIds
     */
    private void setCheckGroupAndCheckItem(Integer groupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            Map<String, Integer> map = new HashMap();
            for (Integer checkitemId : checkitemIds) {
                map.put("checkitemId", checkitemId);
                map.put("groupid", groupId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
