package com.itheima.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * 根据手机号查询是否为会员
     * @param telephone
     * @return
     */
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    /**
     * 自动注册,添加会员
     * @param member
     */
    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    /**
     *获取月份对应的会员数
     * @param monthList
     * @return
     */
    @Override
    public List<Integer> findMemberCountByMonth(List<String> monthList) {
        List<Integer> countMembers=new ArrayList<>();
        for (String month : monthList) {
            countMembers.add(memberDao.findMemberCountBeforeDate(month+"-32"));
        }
        return countMembers;
    }
}
