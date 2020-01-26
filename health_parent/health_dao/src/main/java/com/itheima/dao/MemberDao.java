package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Member;

import java.util.List;

public interface MemberDao {
    public List<Member> findAll();
    public Page<Member> selectByCondition(String queryString);
    //不是会员则添加会员
    public void add(Member member);
    public void deleteById(Integer id);
    public Member findById(Integer id);
    //根据手机号查询是否是会员
    public Member findByTelephone(String telephone);
    public void edit(Member member);
    //根据月份查询对应的会员数
    public Integer findMemberCountBeforeDate(String date);
    public Integer findMemberCountByDate(String date);
    public Integer findMemberCountAfterDate(String date);
    public Integer findMemberTotalCount();


}
