package com.zqt.crowd.service.impl;

import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.entity.po.member.MemberPOExample;
import com.zqt.crowd.mapper.member.MemberPOMapper;
import com.zqt.crowd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zqtao
 * @description: 会员业务层接口具体实现类
 * <p>
 * 注解 @Transactional 开启数据库事务控制 readOnly 属性设置 查询操作为只读
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;


    public MemberPO getMemberpoByLoginAcctRemote(String loginAcct) {

        //1.创建 Example 对象
        MemberPOExample example = new MemberPOExample();
        //2.创建 Criteria 对象
        MemberPOExample.Criteria criteria = example.createCriteria();
        //3.封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        //4.执行查询
        List<MemberPO> list = memberPOMapper.selectByExample(example);
        //5.获取结果
        return list.get(0);
    }
}
