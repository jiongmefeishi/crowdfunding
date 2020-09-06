package com.zqt.crowd.service.impl.member;

import com.zqt.crowd.entity.po.member.MemberPO;
import com.zqt.crowd.entity.po.member.MemberPOExample;
import com.zqt.crowd.mapper.member.MemberPOMapper;
import com.zqt.crowd.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zqtao
 * @description: 会员业务层接口具体实现类
 * <p>
 * 注解 @Transactional 开启数据库事务控制 readOnly 属性设置 查询操作为只读
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberMapper;

    /**
     * 根据登录账户获取用户信息
     *
     * @param loginAcct 登录账户
     * @return 用户信息
     */
    public MemberPO getMemberPOByLoginAcctRemote(String loginAcct) {
        log.info(
                "\n\n执行方法: {} ，方法描述: {} \n",
                "getMemberPOByLoginAcctRemote",
                "根据登录账户获取用户信息"
        );
        //1.创建 Example 对象
        MemberPOExample example = new MemberPOExample();
        //2.创建 Criteria 对象
        MemberPOExample.Criteria criteria = example.createCriteria();
        //3.封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        //4.执行查询
        List<MemberPO> list = memberMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        //5.获取结果
        return list.get(0);
    }

    /**
     * 新增一条会员记录
     *
     * @param memberPO 会员信息
     * @return 影响行数
     * 注解 @Transactional 配置事务
     */
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class,
            readOnly = false)
    public int saveMember(MemberPO memberPO) {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "saveMember",
                "新增一条会员记录"
        );
        return memberMapper.insertSelective(memberPO);
    }
}
