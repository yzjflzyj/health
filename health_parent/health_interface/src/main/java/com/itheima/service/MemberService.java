package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;

public interface MemberService {
    /**
     * 根据号码查会员
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 注册添加会员
     * @param member
     */
    void add(Member member);

    /**
     * 获取月份对应的会员数量
     * @param monthList
     * @return
     */
    List<Integer> findMemberCountByMonth(List<String> monthList);
}
